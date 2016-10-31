package com.sky.simplegank.Expand.presenter.impl;

import com.sky.simplegank.Expand.model.IExpandModel;
import com.sky.simplegank.Expand.model.impl.ExpandModelImpl;
import com.sky.simplegank.Expand.presenter.IExpandPresenter;
import com.sky.simplegank.Expand.view.IExpandView;
import com.sky.simplegank.entity.GankEntity;

import java.util.List;

/**
 * Created by tonycheng on 2016/10/31.
 */

public class ExpandPresenterImpl implements IExpandPresenter, ExpandModelImpl.OnLoadExpandListListener {

    private IExpandModel mExpandModel;
    private IExpandView mExpandView;

    public ExpandPresenterImpl(IExpandView expandView) {
        mExpandView = expandView;
        mExpandModel = new ExpandModelImpl();
    }

    @Override
    public void loadExpandList(int count, int page) {
        mExpandView.showLoading();
        mExpandModel.loadExpandList(count, page, this);
    }

    @Override
    public void onSuccess(List<GankEntity> expandList) {
        mExpandView.hideLoading();
        mExpandView.addExpand(expandList);
    }

    @Override
    public void onFail(Throwable e) {
        mExpandView.hideLoading();
        mExpandView.showLoadFailMsg();
    }
}
