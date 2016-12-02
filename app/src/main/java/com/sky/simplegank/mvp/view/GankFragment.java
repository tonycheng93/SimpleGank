package com.sky.simplegank.mvp.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky.simplegank.R;
import com.sky.simplegank.WebViewActivity;
import com.sky.simplegank.entity.GankEntity;
import com.sky.simplegank.mvp.GankAdapter;
import com.sky.simplegank.mvp.presenter.IGankPresenter;
import com.sky.simplegank.mvp.presenter.impl.GankPresenterImpl;
import com.sky.simplegank.utils.Debugger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class GankFragment extends Fragment implements IGankView, SwipeRefreshLayout.OnRefreshListener {

    public static final String KEY = "type";

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManager;
    private GankAdapter mAdapter;
    private List<GankEntity> mData;
    private IGankPresenter mPresenter;
    private String category;
    private int mCount = 10;
    private int mPage = 1;

    public GankFragment() {
        // Required empty public constructor
    }

    public static GankFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(KEY, type);
        GankFragment fragment = new GankFragment();
        fragment.setArguments(args);
        Debugger.d(fragment.toString());
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new GankPresenterImpl(this);
        category = getArguments().getString(KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_gank, container, false);
        ButterKnife.bind(this, rootView);

        initRefreshLayout();

        initRecyclerView();

        initAdapter();

        onRefresh();

        return rootView;
    }

    private void initRefreshLayout() {
        mRefreshLayout.setOnRefreshListener(this);
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (mAdapter.isShowFooter() && newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == mAdapter.getItemCount()) {
                    //load more
                    mPresenter.loadGankList(category, mCount, mPage);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
                mRefreshLayout.setEnabled(mLayoutManager.findFirstCompletelyVisibleItemPosition() == 0);
            }
        });
    }

    private void initAdapter() {
        mAdapter = new GankAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new GankAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                GankEntity entity = mData.get(position);
                String title = entity.getDesc();
                String url = entity.getUrl();
                Intent intent = WebViewActivity.newIntent(getActivity(), title, url);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showLoading() {
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void addGank(List<GankEntity> entities) {
        mAdapter.setShowFooter(true);
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.addAll(entities);
        if (mPage == 1) {
            mAdapter.setData(mData);
        } else {
            if (entities == null || entities.size() == 0) {
                mAdapter.setShowFooter(false);
            }
            mAdapter.notifyDataSetChanged();
        }
        mAdapter.setData(mData);
        mPage += 1;
    }

    @Override
    public void hideLoading() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoadFailMsg() {
        mRefreshLayout.setRefreshing(false);
        mAdapter.setShowFooter(false);
        View view = mRecyclerView.getRootView();
        Snackbar.make(view, R.string.load_data_error, Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void onRefresh() {
        mPage = 1;
        if (mData != null) {
            mData.clear();
        }
        mPresenter.loadGankList(category, mCount, mPage);
    }
}
