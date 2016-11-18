package com.sky.simplegank.Welfare.presenter;

import android.graphics.Bitmap;

/**
 * Created by tonycheng on 2016/11/7.
 */

public interface IWelfareDetailPresenter {

    void saveImage(Bitmap bitmap, String title);

    void setWallPaper(Bitmap bitmap);
}
