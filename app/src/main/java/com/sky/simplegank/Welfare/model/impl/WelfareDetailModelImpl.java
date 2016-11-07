package com.sky.simplegank.Welfare.model.impl;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

import com.sky.simplegank.Welfare.model.IWelfareDetailModel;
import com.sky.simplegank.utils.BitmapUtil;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by tonycheng on 2016/11/7.
 */

public class WelfareDetailModelImpl implements IWelfareDetailModel {


    @Override
    public void saveImage(final Context context, final Bitmap bitmap, final String title, final OnSaveImageListener listener) {
        Observable.create(new Observable.OnSubscribe<Uri>() {
            @Override
            public void call(Subscriber<? super Uri> subscriber) {
                Uri uri = BitmapUtil.saveBitmapToSDCard(bitmap, title);
                if (uri == null) {
                    subscriber.onError(new Exception("图片保存失败"));
                } else {
                    subscriber.onNext(uri);
                    subscriber.onCompleted();
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Uri>() {
                    @Override
                    public void call(Uri uri) {
                        Intent scannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
                        context.sendBroadcast(scannerIntent);
                        listener.onSuccess(uri);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        listener.onFail(throwable);
                    }
                });
    }

    public interface OnSaveImageListener {
        void onSuccess(Uri uri);

        void onFail(Throwable throwable);
    }
}
