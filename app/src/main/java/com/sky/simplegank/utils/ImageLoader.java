package com.sky.simplegank.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * Created by tonycheng on 2016/10/17.
 */

public class ImageLoader {

    /**
     * 普通加载图片方式
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void display(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    /**
     * 加载资源文件已有的图片
     *
     * @param context
     * @param resId
     * @param imageView
     */
    public static void display(Context context, int resId, ImageView imageView) {
        Glide.with(context)
                .load(resId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    /**
     * 需要对图片进行剪裁
     *
     * @param context
     * @param url
     * @param simpleTarget
     */
    public static void display(Context context, String url, SimpleTarget<Bitmap> simpleTarget) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(simpleTarget);
    }

}
