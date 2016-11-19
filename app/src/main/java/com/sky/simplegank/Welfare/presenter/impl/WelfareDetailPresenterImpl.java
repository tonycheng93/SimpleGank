package com.sky.simplegank.Welfare.presenter.impl;

import android.graphics.Bitmap;
import android.net.Uri;

import com.sky.simplegank.App;
import com.sky.simplegank.Welfare.model.IWelfareDetailModel;
import com.sky.simplegank.Welfare.model.impl.WelfareDetailModelImpl;
import com.sky.simplegank.Welfare.presenter.IWelfareDetailPresenter;
import com.sky.simplegank.Welfare.view.IWelfareDetailView;

/**
 * Created by tonycheng on 2016/11/7.
 */

public class WelfareDetailPresenterImpl implements IWelfareDetailPresenter,
        WelfareDetailModelImpl.OnSaveImageListener, WelfareDetailModelImpl.OnSetWallPaperListener {

    private IWelfareDetailView mWelfareDetailView;
    private IWelfareDetailModel mWelfareDetailModel;

    public WelfareDetailPresenterImpl(IWelfareDetailView welfareDetailView) {
        mWelfareDetailView = welfareDetailView;
        mWelfareDetailModel = new WelfareDetailModelImpl();
    }

    /*------------保存图片------------*/
    @Override
    public void saveImage(Bitmap bitmap, String title) {
        mWelfareDetailView.showProgress();
        mWelfareDetailModel.saveImage(App.getContext(), bitmap, title, this);
    }

    @Override
    public void onSuccess(Uri uri) {
        mWelfareDetailView.showSuccessMsg();
        mWelfareDetailView.hideProgress();
    }

    @Override
    public void onFail(Throwable throwable) {
        mWelfareDetailView.showFailMsg();
        mWelfareDetailView.hideProgress();
    }

    /*----------设置壁纸-------------*/
    @Override
    public void setWallPaper(Bitmap bitmap) {
        mWelfareDetailView.showProgress();
        mWelfareDetailModel.setWallPaper(App.getContext(), bitmap, this);
    }

    @Override
    public void onSetWallPaperSuccess(Bitmap bitmap) {
        mWelfareDetailView.showSuccessMsg();
        mWelfareDetailView.hideProgress();
    }

    @Override
    public void onSetWallPaperFail(Throwable throwable) {
        mWelfareDetailView.showFailMsg();
        mWelfareDetailView.hideProgress();
    }
}
