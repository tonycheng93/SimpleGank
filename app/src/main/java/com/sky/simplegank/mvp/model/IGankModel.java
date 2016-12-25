package com.sky.simplegank.mvp.model;

import com.sky.simplegank.mvp.model.impl.GankModelImpl;

/**
 * Created by tonycheng on 2016/12/1.
 */

public interface IGankModel {
    void loadGank(String category, int count, int page,
                  GankModelImpl.OnLoadGankListener listener);
}
