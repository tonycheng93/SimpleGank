package com.sky.simplegank.CompleteApp.view;

import com.sky.simplegank.entity.GankEntity;

import java.util.List;

/**
 * Created by tonycheng on 2016/10/31.
 */

public interface IAppView {

    void showLoading();

    void addApp(List<GankEntity> appList);

    void hideLoading();

    void showLoadFailMsg();
}
