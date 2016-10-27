package com.sky.simplegank.RestVideo.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.sky.simplegank.R;
import com.sky.simplegank.entity.VideoEntity;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResetVideoFragment extends Fragment implements IVideoView, SwipeRefreshLayout.OnRefreshListener
        , UltimateRecyclerView.OnLoadMoreListener {

    private UltimateRecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;


    public ResetVideoFragment() {
        // Required empty public constructor
    }

    public static ResetVideoFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString("type", type);
        ResetVideoFragment fragment = new ResetVideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_reset_video, container, false);

        onRefresh();

        return rootView;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void addVideo(List<VideoEntity> videoList) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLoadFailMsg() {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void loadMore(int itemsCount, int maxLastVisiblePosition) {

    }
}
