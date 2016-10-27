package com.sky.simplegank.RestVideo.presenter.impl;

import com.sky.simplegank.RestVideo.model.IVideoModel;
import com.sky.simplegank.RestVideo.model.impl.VideoModelImpl;
import com.sky.simplegank.RestVideo.presenter.IVideoPresenter;
import com.sky.simplegank.RestVideo.view.IVideoView;
import com.sky.simplegank.entity.VideoEntity;

import java.util.List;

/**
 * Created by tonycheng on 2016/10/26.
 */

public class VideoPresenterImpl implements IVideoPresenter,
        VideoModelImpl.OnLoadVideoListListener {

    private IVideoView mVideoView;
    private IVideoModel mVideoModel;

    public VideoPresenterImpl(IVideoView videoView) {
        mVideoView = videoView;
        mVideoModel = new VideoModelImpl();
    }

    @Override
    public void loadVideoList(int count, int page) {
        mVideoView.showLoading();
        mVideoModel.loadVideoList(count, page, this);
    }

    @Override
    public void onSuccess(List<VideoEntity> videoList) {
        mVideoView.hideLoading();
        mVideoView.addVideo(videoList);
    }

    @Override
    public void onFail(Throwable e) {
        mVideoView.hideLoading();
        mVideoView.showLoadFailMsg();
    }
}
