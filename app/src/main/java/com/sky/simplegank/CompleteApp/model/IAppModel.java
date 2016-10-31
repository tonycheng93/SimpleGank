package com.sky.simplegank.CompleteApp.model;

import com.sky.simplegank.CompleteApp.model.impl.AppModelImpl;

/**
 * Created by tonycheng on 2016/10/31.
 */

public interface IAppModel {
    void loadAppList(int count, int page,
                     AppModelImpl.OnLoadAppListListener listener);
}
