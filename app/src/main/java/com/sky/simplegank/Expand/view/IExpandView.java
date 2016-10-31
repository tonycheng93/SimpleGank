package com.sky.simplegank.Expand.view;

import com.sky.simplegank.entity.GankEntity;

import java.util.List;

/**
 * Created by tonycheng on 2016/10/31.
 */

public interface IExpandView {

    void showLoading();

    void addExpand(List<GankEntity> expandList);

    void hideLoading();

    void showLoadFailMsg();
}
