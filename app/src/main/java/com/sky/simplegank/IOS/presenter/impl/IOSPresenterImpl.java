package com.sky.simplegank.IOS.presenter.impl;

import com.sky.simplegank.IOS.model.IIOSModel;
import com.sky.simplegank.IOS.model.impl.IOSModelImpl;
import com.sky.simplegank.IOS.presenter.IIOSPresenter;
import com.sky.simplegank.IOS.view.IIOSView;
import com.sky.simplegank.entity.GankEntity;

import java.util.List;

/**
 * 项目名称：SimpleGank
 * 类描述：
 * 创建人：tonycheng
 * 创建时间：2016/10/30 14:43
 * 邮箱：tonycheng93@outlook.com
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class IOSPresenterImpl implements IIOSPresenter, IOSModelImpl.OnLoadIOSListListener {

    private IIOSModel mIIOSModel;
    private IIOSView mIIOSView;

    public IOSPresenterImpl(IIOSView iiosView) {
        this.mIIOSView = iiosView;
        mIIOSModel = new IOSModelImpl();
    }

    @Override
    public void loadIOSList(int count, int page) {
        mIIOSView.showLoading();
        mIIOSModel.onLoadIOSList(count, page, this);
    }

    @Override
    public void onSuccess(List<GankEntity> iOSList) {
        mIIOSView.hideLoading();
        mIIOSView.addIOSList(iOSList);
    }

    @Override
    public void onFail(Throwable e) {
        mIIOSView.hideLoading();
        mIIOSView.showLoadFailMsg();
    }
}
