package com.sky.simplegank.Welfare.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marshalchen.ultimaterecyclerview.RecyclerItemClickListener;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.sky.simplegank.R;
import com.sky.simplegank.Welfare.WelfareAdapter;
import com.sky.simplegank.Welfare.presenter.IWelfarePresenter;
import com.sky.simplegank.Welfare.presenter.impl.WelfarePresenterImpl;
import com.sky.simplegank.entity.GankEntity;
import com.sky.simplegank.utils.Debugger;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WelfareFragment extends Fragment implements IWelfareView,
        SwipeRefreshLayout.OnRefreshListener, UltimateRecyclerView.OnLoadMoreListener {

    private static final String TAG = "WelfareFragment";
    public static final String PICTURE_URL_FLAG = "url";
    public static final String PICTURE_TIME_FLAG = "time";

    private UltimateRecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mLayoutManager;
    private WelfareAdapter mAdapter;

    private IWelfarePresenter mWelfarePresenter;
    private List<GankEntity> mData;
    private int mCount = 10;
    private int mPage = 1;

    public WelfareFragment() {
        // Required empty public constructor
    }

    public static WelfareFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString("type", type);
        WelfareFragment fragment = new WelfareFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWelfarePresenter = new WelfarePresenterImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_welfare, container, false);

        mRecyclerView = (UltimateRecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(false);

        mRecyclerView.setDefaultSwipeToRefreshColorScheme(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        mRecyclerView.enableDefaultSwipeRefresh(true);
        mRecyclerView.setDefaultOnRefreshListener(this);

        mRecyclerView.reenableLoadmore();

        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new WelfareAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnLoadMoreListener(this);

        mRecyclerView.addOnItemTouchListener(mItemClickListener);

        onRefresh();

        return rootView;
    }

    @Override
    public void showLoading() {
        mRecyclerView.setRefreshing(true);
    }


    @Override
    public void addWelfare(List<GankEntity> welfareList) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
//        mData.clear();//不能clear，否则下拉刷新会重新填充list
        mData.addAll(welfareList);
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
        /**
         * 会出现Issues #1的问题
         */
//        if (mData != null) {
//            mData.clear();
//        }
        mWelfarePresenter.loadWelfareList(mCount, mPage);
        Debugger.d(TAG, "下拉刷新...");
    }

    @Override
    public void loadMore(int itemsCount, int maxLastVisiblePosition) {
        mWelfarePresenter.loadWelfareList(mCount, ++mPage);
        Debugger.d(TAG, "加载更多...");
    }

    private RecyclerItemClickListener mItemClickListener = new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Intent intent = new Intent(getActivity(), WelfareDetailActivity.class);
            String pictureUrl = mData.get(position).getUrl();
            String pictureTime = mData.get(position).getPublishedAt().split("T")[0];
            intent.putExtra(PICTURE_URL_FLAG, pictureUrl);
            intent.putExtra(PICTURE_TIME_FLAG, pictureTime);
            startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), view, "sharedNames").toBundle());
        }
    });
}
