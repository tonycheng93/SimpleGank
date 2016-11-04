package com.sky.simplegank.Welfare.model;

import com.sky.simplegank.Welfare.model.impl.PictureModelImpl;

/**
 * Created by tonycheng on 2016/11/4.
 */

public interface IPictureModel {
    //保存图片
    void savePicture(PictureModelImpl.OnSavePictureListener listener);

    //分享图片
    void sharePicture(PictureModelImpl.OnSharePictureListener listener);

    //设置壁纸
    void setWallpaper(PictureModelImpl.OnSetWallpaperListener listener);
}
