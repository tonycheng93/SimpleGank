package com.sky.simplegank.Welfare.view;

import com.sky.simplegank.entity.GankEntity;

import java.util.List;

/**
 * Created by tonycheng on 2016/10/14.
 */

public interface IWelfareView {

    void showLoading();

    void addWelfare(List<GankEntity> welfareList);

    void hideLoading();

    void showLoadFailMsg();
}
