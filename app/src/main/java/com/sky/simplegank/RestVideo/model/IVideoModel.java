package com.sky.simplegank.RestVideo.model;

import com.sky.simplegank.RestVideo.model.impl.VideoModelImpl;

/**
 * Created by tonycheng on 2016/10/26.
 */

public interface IVideoModel{
    void loadVideoList(int count, int page, VideoModelImpl.OnLoadVideoListListener listener);
}
