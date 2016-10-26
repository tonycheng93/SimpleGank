package com.sky.simplegank.Android.model;

import com.sky.simplegank.Android.model.impl.AndroidModelImpl;

/**
 * Created by tonycheng on 2016/10/25.
 */

public interface IAndroidModel {

    void loadAndroidList(int count, int page,
                         AndroidModelImpl.OnLoadAndroidListListener listener);
}
