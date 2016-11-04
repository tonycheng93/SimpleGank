package com.sky.simplegank.Welfare.presenter.impl;

import com.sky.simplegank.Welfare.model.IPictureModel;
import com.sky.simplegank.Welfare.model.impl.PictureModelImpl;
import com.sky.simplegank.Welfare.presenter.IPicturePresenter;
import com.sky.simplegank.Welfare.view.IPictureView;

/**
 * Created by tonycheng on 2016/11/4.
 */

public class PicturePresenterImpl implements IPicturePresenter, PictureModelImpl.OnSavePictureListener
        , PictureModelImpl.OnSharePictureListener, PictureModelImpl.OnSetWallpaperListener {

    private IPictureView mPictureView;
    private IPictureModel mPictureModel;

    public PicturePresenterImpl(IPictureView pictureView) {
        mPictureView = pictureView;
        mPictureModel = new PictureModelImpl();
    }

    @Override
    public void savePicture() {
        mPictureModel.savePicture(this);
        mPictureView.showProgressSuccess("保存成功！");
    }

    @Override
    public void sharePicture() {
        mPictureModel.sharePicture(this);
        mPictureView.showProgressSuccess("分享成功！");
    }

    @Override
    public void setWallpaper() {
        mPictureModel.setWallpaper(this);
        mPictureView.showProgressSuccess("设置成功！");
    }

    @Override
    public void onSavePictureSuccess() {
        mPictureView.hideProgressDialog();
    }

    @Override
    public void onSavePictureFail() {
        mPictureView.showProgressFail("保存失败！");
        mPictureView.hideProgressDialog();
    }

    @Override
    public void onSharePictureSuccess() {
        mPictureView.hideProgressDialog();
    }

    @Override
    public void onSharePictureFail() {
        mPictureView.showProgressFail("分享失败！");
        mPictureView.hideProgressDialog();
    }

    @Override
    public void OnSetWallpaperSuccess() {
        mPictureView.hideProgressDialog();
    }

    @Override
    public void OnSetWallpaperFail() {
        mPictureView.showProgressFail("设置失败！");
        mPictureView.hideProgressDialog();
    }
}
