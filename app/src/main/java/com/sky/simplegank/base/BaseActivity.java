package com.sky.simplegank.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by tonycheng on 2016/11/3.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getData();
    }

    public abstract void getData();
}
