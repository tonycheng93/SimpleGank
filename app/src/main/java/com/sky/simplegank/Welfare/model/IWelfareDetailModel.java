package com.sky.simplegank.Welfare.model;

import android.content.Context;
import android.graphics.Bitmap;

import com.sky.simplegank.Welfare.model.impl.WelfareDetailModelImpl;

/**
 * Created by tonycheng on 2016/11/7.
 */

public interface IWelfareDetailModel {

    void saveImage(Context context, Bitmap bitmap, String title,
                   WelfareDetailModelImpl.OnSaveImageListener listener);

    void setWallPaper(Context context, Bitmap bitmap,
                      WelfareDetailModelImpl.OnSetWallPaperListener listener);
}
