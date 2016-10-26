package com.sky.simplegank.Android.presenter.impl;

import com.sky.simplegank.Android.model.IAndroidModel;
import com.sky.simplegank.Android.model.impl.AndroidModelImpl;
import com.sky.simplegank.Android.presenter.IAndroidPresenter;
import com.sky.simplegank.Android.view.IAndroidView;
import com.sky.simplegank.entity.GankEntity;

import java.util.List;

/**
 * Created by tonycheng on 2016/10/25.
 */

public class AndroidPresenterImpl implements IAndroidPresenter,
        AndroidModelImpl.OnLoadAndroidListListener {

    private IAndroidModel mIAndroidModel;
    private IAndroidView mIAndroidView;

    public AndroidPresenterImpl(IAndroidView androidView) {
        this.mIAndroidView = androidView;
        mIAndroidModel = new AndroidModelImpl();
    }

    @Override
    public void loadAndroidList(int count, int page) {
        mIAndroidView.showLoading();
        mIAndroidModel.loadAndroidList(count, page, this);
    }

    @Override
    public void onSuccess(List<GankEntity> androidList) {
        mIAndroidView.hideLoading();
        mIAndroidView.addWelfare(androidList);
    }

    @Override
    public void onFail(Throwable e) {
        mIAndroidView.hideLoading();
        mIAndroidView.showLoadFailMsg();
    }
}
