package com.sky.simplegank.RestVideo.view;

import com.sky.simplegank.entity.VideoEntity;

import java.util.List;

/**
 * Created by tonycheng on 2016/10/26.
 */

public interface IVideoView {

    void showLoading();

    void addVideo(List<VideoEntity> videoList);

    void hideLoading();

    void showLoadFailMsg();
}
