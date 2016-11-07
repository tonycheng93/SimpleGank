package com.sky.simplegank.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by tonycheng on 2016/11/7.
 */

public class BitmapUtil {

    public static Observable<Uri> saveImageObservable(final Context context, final String url) {

        return Observable.create(new Observable.OnSubscribe<Uri>() {
            @Override
            public void call(Subscriber<? super Uri> subscriber) {
              final Bitmap bitmap = null;

            }
        });
    }
}
