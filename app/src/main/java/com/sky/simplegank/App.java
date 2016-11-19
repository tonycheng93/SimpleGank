package com.sky.simplegank;

import android.app.Application;
import android.content.Context;

import com.sky.simplegank.http.CrashHandler;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by tonycheng on 2016/10/14.
 */

public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化异常捕获
        initCrash();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        mContext = getApplicationContext();
    }

    private void initCrash() {
        //在这里为应用设置异常处理程序，然后我们的程序才能捕获未处理的异常
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
    }

    public static Context getContext() {
        return mContext;
    }
}
