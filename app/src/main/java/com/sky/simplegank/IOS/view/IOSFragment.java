package com.sky.simplegank.IOS.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.sky.simplegank.IOS.IOSAdapter;
import com.sky.simplegank.IOS.presenter.IIOSPresenter;
import com.sky.simplegank.IOS.presenter.impl.IOSPresenterImpl;
import com.sky.simplegank.R;
import com.sky.simplegank.entity.GankEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class IOSFragment extends Fragment implements IIOSView,
        SwipeRefreshLayout.OnRefreshListener, UltimateRecyclerView.OnLoadMoreListener {

    private UltimateRecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private IOSAdapter mAdapter;


    private IIOSPresenter mPresenter;
    private List<GankEntity> mData;
    private int mCount = 10;
    private int mPage = 1;

    public static boolean mUserVisibleHint;

    public IOSFragment() {
        // Required empty public constructor
    }

    public static IOSFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString("type", type);
        IOSFragment fragment = new IOSFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new IOSPresenterImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ios, container, false);

        mRecyclerView = (UltimateRecyclerView) rootView.findViewById(R.id.ios_recycler_view);
        mRecyclerView.setHasFixedSize(false);

        mRecyclerView.enableDefaultSwipeRefresh(true);
        mRecyclerView.setDefaultOnRefreshListener(this);
        mRecyclerView.reenableLoadmore();
        mRecyclerView.setOnLoadMoreListener(this);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new IOSAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        onRefresh();

        return rootView;
    }

    @Override
    public void onResume() {
        setUserVisibleHint(true);
        mUserVisibleHint = getUserVisibleHint();
        super.onResume();
    }

    @Override
    public void onPause() {
        setUserVisibleHint(false);
        mUserVisibleHint = getUserVisibleHint();
        super.onPause();
    }

    @Override
    public void showLoading() {
        mRecyclerView.setRefreshing(true);
    }

    @Override
    public void addIOSList(List<GankEntity> iOSList) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.addAll(iOSList);
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
        mPresenter.loadIOSList(mCount, mPage);
    }

    @Override
    public void loadMore(int itemsCount, int maxLastVisiblePosition) {
        mPresenter.loadIOSList(mCount, ++mPage);
    }
}
