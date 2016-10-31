package com.sky.simplegank.Expand.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.sky.simplegank.Expand.ExpandAdapter;
import com.sky.simplegank.Expand.presenter.IExpandPresenter;
import com.sky.simplegank.Expand.presenter.impl.ExpandPresenterImpl;
import com.sky.simplegank.R;
import com.sky.simplegank.entity.GankEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExpandFragment extends Fragment implements IExpandView, SwipeRefreshLayout.OnRefreshListener,
        UltimateRecyclerView.OnLoadMoreListener {


    private UltimateRecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    private IExpandPresenter mPresenter;
    private ExpandAdapter mAdapter;
    private List<GankEntity> mData;
    private int mCount = 10;
    private int mPage = 1;

    public ExpandFragment() {
        // Required empty public constructor
    }

    public static ExpandFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString("type", type);
        ExpandFragment fragment = new ExpandFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new ExpandPresenterImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_expand, container, false);

        mRecyclerView = (UltimateRecyclerView) rootView.findViewById(R.id.expand_recycler_view);
        mRecyclerView.setHasFixedSize(false);

        mRecyclerView.enableDefaultSwipeRefresh(true);
        mRecyclerView.setDefaultOnRefreshListener(this);
        mRecyclerView.reenableLoadmore();
        mRecyclerView.setOnLoadMoreListener(this);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ExpandAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        onRefresh();

        return rootView;
    }

    @Override
    public void onRefresh() {
        mPresenter.loadExpandList(mCount, mPage);
    }

    @Override
    public void showLoading() {
        mRecyclerView.setRefreshing(true);
    }

    @Override
    public void addExpand(List<GankEntity> expandList) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.addAll(expandList);
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
    public void loadMore(int itemsCount, int maxLastVisiblePosition) {
        mPresenter.loadExpandList(mCount, ++mPage);
    }
}
