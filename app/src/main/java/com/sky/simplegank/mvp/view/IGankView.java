package com.sky.simplegank.mvp.view;

import com.sky.simplegank.entity.GankEntity;

import java.util.List;

/**
 * Created by tonycheng on 2016/12/1.
 */

public interface IGankView {

    void showLoading();

    void addGank(List<GankEntity> entities);

    void hideLoading();

    void showLoadFailMsg();
}
