package com.sky.simplegank.Welfare.presenter.impl;

import com.sky.simplegank.Welfare.model.IWelfareModel;
import com.sky.simplegank.Welfare.model.impl.WelfareModelImpl;
import com.sky.simplegank.Welfare.presenter.IWelfarePresenter;
import com.sky.simplegank.Welfare.view.IWelfareView;
import com.sky.simplegank.entity.GankEntity;

import java.util.List;

/**
 * Created by tonycheng on 2016/10/17.
 */

public class WelfarePresenterImpl implements IWelfarePresenter,
        WelfareModelImpl.OnLoadWelfareListListener {

    private IWelfareView mIWelfareView;
    private IWelfareModel mIWelfareModel;

    public WelfarePresenterImpl(IWelfareView view) {
        this.mIWelfareView = view;
        mIWelfareModel = new WelfareModelImpl();
    }

    @Override
    public void loadWelfareList(int count, int page) {
        mIWelfareView.showLoading();
        mIWelfareModel.loadWelfareList(count, page, this);
    }

    @Override
    public void onSuccess(List<GankEntity> welfareList) {
        mIWelfareView.addWelfare(welfareList);
        mIWelfareView.hideLoading();
    }

    @Override
    public void onFail(Throwable e) {
        mIWelfareView.hideLoading();
        mIWelfareView.showLoadFailMsg();
    }
}
