package com.sky.simplegank.FrontEnd.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.sky.simplegank.FrontEnd.FrontEndAdapter;
import com.sky.simplegank.FrontEnd.presenter.IFrontEndPresenter;
import com.sky.simplegank.FrontEnd.presenter.impl.FrontEndPresenterImpl;
import com.sky.simplegank.R;
import com.sky.simplegank.entity.GankEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FrontEndFragment extends Fragment implements IFrontEndView, SwipeRefreshLayout
        .OnRefreshListener,
        UltimateRecyclerView.OnLoadMoreListener {

    private UltimateRecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    private IFrontEndPresenter mPresenter;
    private List<GankEntity> mData;
    private FrontEndAdapter mAdapter;
    private int mCount = 10;
    private int mPage = 1;

    public FrontEndFragment() {
        // Required empty public constructor
    }

    public static FrontEndFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString("type", type);
        FrontEndFragment fragment = new FrontEndFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new FrontEndPresenterImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_front_end, container, false);
        mRecyclerView = (UltimateRecyclerView) rootView.findViewById(R.id.front_end_recycler_view);
        mRecyclerView.setHasFixedSize(false);

        mRecyclerView.enableDefaultSwipeRefresh(true);
        mRecyclerView.setDefaultOnRefreshListener(this);
        mRecyclerView.reenableLoadmore();
        mRecyclerView.setOnLoadMoreListener(this);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new FrontEndAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        onRefresh();

        return rootView;
    }

    @Override
    public void showLoading() {
        mRecyclerView.setRefreshing(true);
    }

    @Override
    public void addFrontEnd(List<GankEntity> frontEndList) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.addAll(frontEndList);
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
        mPresenter.loadFrontEndList(mCount, mPage);
    }

    @Override
    public void loadMore(int itemsCount, int maxLastVisiblePosition) {
        mPresenter.loadFrontEndList(mCount, ++mPage);
    }
}
