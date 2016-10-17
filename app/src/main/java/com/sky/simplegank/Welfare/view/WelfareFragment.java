package com.sky.simplegank.Welfare.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky.simplegank.R;
import com.sky.simplegank.Welfare.WelfareAdapter;
import com.sky.simplegank.Welfare.presenter.IWelfarePresenter;
import com.sky.simplegank.Welfare.presenter.impl.WelfarePresenterImpl;
import com.sky.simplegank.entity.GankEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WelfareFragment extends Fragment implements IWelfareView,
        SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "WelfareFragment";

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
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

        mRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_widget);
        mRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        mRefreshLayout.setOnRefreshListener(this);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new WelfareAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        onRefresh();

        return rootView;
    }

    @Override
    public void showLoading() {
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void addWelfare(List<GankEntity> welfareList) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.addAll(welfareList);
        mAdapter.setData(mData);
    }

    @Override
    public void hideLoading() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoadFailMsg() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        if (mData != null) {
            mData.clear();
        }
        mWelfarePresenter.loadWelfareList(mCount, mPage);
    }
}
