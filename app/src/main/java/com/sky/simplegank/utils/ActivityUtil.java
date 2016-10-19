package com.sky.simplegank.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by tonycheng on 2016/10/17.
 */

public class ActivityUtil {

    public static void addActivityToFragment(FragmentManager manager, int containerViewId, Fragment fragment) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(containerViewId, fragment);
        transaction.commit();
    }
}
