package com.sky.simplegank.CompleteApp.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.sky.simplegank.CompleteApp.AppAdapter;
import com.sky.simplegank.CompleteApp.presenter.IAppPresenter;
import com.sky.simplegank.CompleteApp.presenter.impl.AppPresenterImpl;
import com.sky.simplegank.R;
import com.sky.simplegank.entity.GankEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppFragment extends Fragment implements IAppView,
        SwipeRefreshLayout.OnRefreshListener, UltimateRecyclerView.OnLoadMoreListener {

    private UltimateRecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    private IAppPresenter mPresenter;
    private AppAdapter mAdapter;
    private List<GankEntity> mData;
    private int mCount = 10;
    private int mPage = 1;

    public AppFragment() {
        // Required empty public constructor
    }

    public static AppFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString("type", type);
        AppFragment fragment = new AppFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new AppPresenterImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_app, container, false);

        mRecyclerView = (UltimateRecyclerView) rootView.findViewById(R.id.app_recycler_view);
        mRecyclerView.setHasFixedSize(false);

        mRecyclerView.enableDefaultSwipeRefresh(true);
        mRecyclerView.setDefaultOnRefreshListener(this);
        mRecyclerView.reenableLoadmore();
        mRecyclerView.setOnLoadMoreListener(this);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new AppAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        onRefresh();

        return rootView;
    }

    @Override
    public void showLoading() {
        mRecyclerView.setRefreshing(true);
    }

    @Override
    public void addApp(List<GankEntity> appList) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.addAll(appList);
        mAdapter.setData(mData);
    }

    @Override
    public void hideLoading() {
        mRecyclerView.setRefreshing(false);
    }

    @Override
    public void showLoadFailMsg() {
        mRecyclerView.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        mPresenter.loadAppList(mCount, mPage);
    }

    @Override
    public void loadMore(int itemsCount, int maxLastVisiblePosition) {
        mPresenter.loadAppList(mCount, ++mPage);
    }
}
