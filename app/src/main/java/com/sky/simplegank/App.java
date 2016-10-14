package com.sky.simplegank;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by tonycheng on 2016/10/14.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
