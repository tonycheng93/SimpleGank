package com.sky.simplegank.mvp.presenter.impl;

import com.sky.simplegank.entity.GankEntity;
import com.sky.simplegank.mvp.model.IGankModel;
import com.sky.simplegank.mvp.model.impl.GankModelImpl;
import com.sky.simplegank.mvp.presenter.IGankPresenter;
import com.sky.simplegank.mvp.view.IGankView;

import java.util.List;

/**
 * Created by tonycheng on 2016/12/1.
 */

public class GankPresenterImpl implements IGankPresenter, GankModelImpl.OnLoadGankListener {

    private IGankView mGankView;
    private IGankModel mGankModel;

    public GankPresenterImpl(IGankView gankView) {
        mGankView = gankView;
        mGankModel = new GankModelImpl();
    }

    @Override
    public void loadGankList(String category, int count, int page) {
        mGankView.showLoading();
        mGankModel.loadGank(category, count, page, this);
    }

    @Override
    public void onSuccess(List<GankEntity> entities) {
        mGankView.addGank(entities);
        mGankView.hideLoading();
    }

    @Override
    public void onFail(Throwable throwable) {
        mGankView.hideLoading();
        mGankView.showLoadFailMsg();
    }
}
