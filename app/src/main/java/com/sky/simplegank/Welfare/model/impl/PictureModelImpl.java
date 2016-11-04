package com.sky.simplegank.Welfare.model.impl;

import com.sky.simplegank.Welfare.model.IPictureModel;

/**
 * Created by tonycheng on 2016/11/4.
 */

public class PictureModelImpl implements IPictureModel {

    @Override
    public void savePicture(OnSavePictureListener listener) {

    }

    @Override
    public void sharePicture(OnSharePictureListener listener) {

    }

    @Override
    public void setWallpaper(OnSetWallpaperListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
            }
        }).start();
    }

    public interface OnSavePictureListener {
        void onSavePictureSuccess();

        void onSavePictureFail();
    }

    public interface OnSharePictureListener {
        void onSharePictureSuccess();

        void onSharePictureFail();
    }

    public interface OnSetWallpaperListener {
        void OnSetWallpaperSuccess();

        void OnSetWallpaperFail();
    }

}
