package com.sky.simplegank.http;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tonycheng on 2016/11/7.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = "CrashHandler";

    private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/SimpleGank/CrashLog";
    private static final String FILE_NAME = "crash";

    //log文件的后缀名
    private static final String FILE_NAME_SUFFIX = ".txt";

    private volatile static CrashHandler sInstance;

    //系统默认的异常处理（默认情况下，系统会终止当前的异常程序）
    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;

    private Context mContext;
    private String crashInfo;

    //私有化构造函数
    private CrashHandler() {

    }

    public static CrashHandler getInstance() {
        if (sInstance == null) {
            synchronized (CrashHandler.class) {
                if (sInstance == null) {
                    sInstance = new CrashHandler();
                }
            }
        }
        return sInstance;
    }

    //做一些初始化操作
    public void init(Context context) {
        //获取系统默认的异常处理器
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        //将当前实例设为系统默认的异常处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
        //获取Context，方便内部使用
        mContext = context.getApplicationContext();
    }

    /**
     * 这个是最关键的函数，当程序中有未被捕获的异常，系统将会自动调用#uncaughtException方法
     * thread为出现未捕获异常的线程，e为未捕获的异常，有了这个e，我们就可以得到异常信息。
     *
     * @param thread
     * @param ex
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        try {
            //导出异常信息到SD卡中
            dumpExceptionToSDCard(ex);
            //这里可以通过网络上传异常信息到服务器，便于开发人员分析日志从而解决bug
            uploadExceptionToServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //打印出当前调用栈的信息
        ex.printStackTrace();
        //这里可以弹出自己自定义的程序崩溃页面：然后自己干掉自己；
        //如果系统提供了默认的异常处理器，则交给系统去结束我们的程序，否则就由我们自己结束自己
        if (mDefaultCrashHandler != null) {
            mDefaultCrashHandler.uncaughtException(thread, ex);
        } else {
            //干掉当前的程序
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

    private void dumpExceptionToSDCard(Throwable ex) throws IOException {
        //如果SD卡不存在或者无法使用，则无法把异常信息写入SD卡
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.d(TAG, "sdcard unmounted,skip dump exception");
            return;
        }
        File dir = new File(PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        long current = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date(current));

        //以当前时间创建log文件
        File file = new File(dir, FILE_NAME + "_" + time.trim() + FILE_NAME_SUFFIX);
        if (!file.exists()) {
            file.createNewFile();
        }
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            //导出发生异常的时间
            writer.println(time);
            //导出手机信息
            dumpPhoneInfo(writer);
            //导出异常的调用栈信息
            ex.printStackTrace(writer);
            crashInfo = writer.toString();
            writer.close();
        } catch (Exception e) {
            Log.d(TAG, "dump crash info failed:" + e.toString());
        }
    }

    private void dumpPhoneInfo(PrintWriter writer) throws PackageManager.NameNotFoundException {
        //应用版本名称和版本号
        PackageManager pm = mContext.getPackageManager();
        PackageInfo info = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
        writer.print("App Version:");
        writer.print(info.versionName);
        writer.print("_");
        writer.print(info.versionCode);

        //Android版本号
        writer.print("OS Version:");
        writer.print(Build.VERSION.RELEASE);
        writer.print("_");
        writer.print(Build.VERSION.SDK_INT);

        //手机制造商
        writer.print("Vendor:");
        writer.print(Build.MANUFACTURER);

        //手机型号
        writer.print("Model: ");
        writer.println(Build.MODEL);

        //cpu架构
        writer.print("CPU ABI: ");
        writer.print(Build.CPU_ABI);
    }

    private void uploadExceptionToServer() {
        //TODO Upload Exception Message To Your Web Server
    }
}
