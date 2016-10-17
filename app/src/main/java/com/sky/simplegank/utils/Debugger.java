package com.sky.simplegank.utils;

import android.util.Log;

import com.sky.simplegank.config.Constant;

/**
 * Created by tonycheng on 2016/10/17.
 */

public class Debugger {

    private static final String LOG_TAG = "SimpleGank";

    enum LOG_IDENTIFY {
        INFO,
        DEBUG,
        WARNING,
        ERROR,
        P
    }

    public static void i(String info) {
        if (Constant.IS_DEBUG)
        print(LOG_IDENTIFY.INFO, LOG_TAG, info);
    }

    public static void d(String info) {
        if (Constant.IS_DEBUG)
        print(LOG_IDENTIFY.DEBUG, LOG_TAG, info);
    }

    public static void w(String info) {
        if (Constant.IS_DEBUG)
        print(LOG_IDENTIFY.WARNING, LOG_TAG, info);
    }

    public static void e(String info) {
        if (Constant.IS_DEBUG)
        print(LOG_IDENTIFY.ERROR, LOG_TAG, info);
    }

    public static void i(String TAG, String info) {
        if (Constant.IS_DEBUG)
        print(LOG_IDENTIFY.INFO, TAG, info);
    }

    public static void d(String TAG, String info) {
        if (Constant.IS_DEBUG)
        print(LOG_IDENTIFY.DEBUG, TAG, info);
    }

    public static void w(String TAG, String info) {
        if (Constant.IS_DEBUG)
        print(LOG_IDENTIFY.WARNING, TAG, info);
    }

    public static void e(String TAG, String info) {
        if (Constant.IS_DEBUG)
        print(LOG_IDENTIFY.ERROR, TAG, info);
    }

    public static void p(String info) {
        if (Constant.IS_DEBUG)
        print(LOG_IDENTIFY.P, LOG_TAG, info);
    }

    // 0 - dalvik.system.VMStack.getThreadStackTrace()
    // 1 - java.lang.Thread.getStackTrace()
    // 2 - Debugger.print()
    // 3 - Debugger.i/d/w/e();
    // 4 - the line call Debugger.i/d/w/e();
    private static void print(LOG_IDENTIFY mode, String TAG, String info) {
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        StringBuffer sb = new StringBuffer();
        sb.append(ste[4].getClassName());
        sb.append("$");
        sb.append(ste[4].getMethodName());
        sb.append("()@");
        sb.append(ste[4].getLineNumber());
        sb.append(" : ");
        sb.append(info);

        switch (mode) {
            case INFO:
                Log.i(TAG, sb.toString());
                break;
            case DEBUG:
                Log.d(TAG, sb.toString());
                break;
            case WARNING:
                Log.w(TAG, sb.toString());
                break;
            case ERROR:
                Log.e(TAG, sb.toString());
                break;
            case P:
                if (ste.length > 5) {
                    sb.setLength(0);
                    sb.append(ste[5].getClassName());
                    sb.append("$");
                    sb.append(ste[5].getMethodName());
                    sb.append("()@");
                    sb.append(ste[5].getLineNumber());
                    sb.append(" : ");
                    sb.append(info);
                }
                Log.d(TAG, sb.toString());
                break;
            default:
                break;
        }
    }
}
