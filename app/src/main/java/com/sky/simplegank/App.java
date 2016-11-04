package com.sky.simplegank;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by tonycheng on 2016/10/14.
 */

public class App extends Application {

    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
