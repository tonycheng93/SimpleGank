package com.sky.simplegank.FrontEnd.presenter.impl;

import com.sky.simplegank.FrontEnd.model.IFrontEndModel;
import com.sky.simplegank.FrontEnd.model.impl.FrontEndModelImpl;
import com.sky.simplegank.FrontEnd.presenter.IFrontEndPresenter;
import com.sky.simplegank.FrontEnd.view.IFrontEndView;
import com.sky.simplegank.entity.GankEntity;

import java.util.List;

/**
 * 项目名称：SimpleGank
 * 类描述：
 * 创建人：tonycheng
 * 创建时间：2016/10/30 21:49
 * 邮箱：tonycheng93@outlook.com
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class FrontEndPresenterImpl implements IFrontEndPresenter,
        FrontEndModelImpl.OnLoadFrontEndListListener {

    private IFrontEndView mFrontEndView;
    private IFrontEndModel mFrontEndModel;

    public FrontEndPresenterImpl(IFrontEndView frontEndView) {
        mFrontEndView = frontEndView;
        mFrontEndModel = new FrontEndModelImpl();
    }

    @Override
    public void loadFrontEndList(int count, int page) {
        mFrontEndView.showLoading();
        mFrontEndModel.loadFrontEndList(count, page, this);
    }

    @Override
    public void onSuccess(List<GankEntity> frontEndList) {
        mFrontEndView.hideLoading();
        mFrontEndView.addFrontEnd(frontEndList);
    }

    @Override
    public void onFail(Throwable e) {
        mFrontEndView.hideLoading();
        mFrontEndView.showLoadFailMsg();
    }
}
