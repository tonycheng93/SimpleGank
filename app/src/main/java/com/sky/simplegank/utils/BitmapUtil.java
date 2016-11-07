package com.sky.simplegank.utils;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by tonycheng on 2016/11/7.
 */

public class BitmapUtil {

    private BitmapUtil() {
        throw new UnsupportedOperationException("can not be instanced.");
    }

    public static boolean isSDCardEnable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static Uri saveBitmapToSDCard(Bitmap bitmap, String title) {
        File appDir = new File(Environment.getExternalStorageDirectory(), "SimpleGank");
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        String fileName = title.replace("/", "-") + ".jpg";
        File file = new File(appDir, fileName);
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(file);
            assert bitmap != null;
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.close();
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return Uri.fromFile(file);
    }


}
