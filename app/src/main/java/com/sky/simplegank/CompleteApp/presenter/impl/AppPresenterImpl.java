package com.sky.simplegank.CompleteApp.presenter.impl;

import com.sky.simplegank.CompleteApp.model.IAppModel;
import com.sky.simplegank.CompleteApp.model.impl.AppModelImpl;
import com.sky.simplegank.CompleteApp.presenter.IAppPresenter;
import com.sky.simplegank.CompleteApp.view.IAppView;
import com.sky.simplegank.entity.GankEntity;

import java.util.List;

/**
 * Created by tonycheng on 2016/10/31.
 */

public class AppPresenterImpl implements IAppPresenter, AppModelImpl.OnLoadAppListListener {


    private IAppView mAppView;
    private IAppModel mAppModel;

    public AppPresenterImpl(IAppView appView) {
        mAppView = appView;
        mAppModel = new AppModelImpl();
    }

    @Override
    public void loadAppList(int count, int page) {
        mAppView.showLoading();
        mAppModel.loadAppList(count, page, this);
    }

    @Override
    public void onSuccess(List<GankEntity> appList) {
        mAppView.hideLoading();
        mAppView.addApp(appList);
    }

    @Override
    public void onFail(Throwable e) {
        mAppView.hideLoading();
        mAppView.showLoadFailMsg();
    }
}
