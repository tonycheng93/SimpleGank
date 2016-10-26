package com.sky.simplegank.Android.view;

import com.sky.simplegank.entity.GankEntity;

import java.util.List;

/**
 * Created by tonycheng on 2016/10/25.
 */

public interface IAndroidView {

    void showLoading();

    void addWelfare(List<GankEntity> androidList);

    void hideLoading();

    void showLoadFailMsg();
}
