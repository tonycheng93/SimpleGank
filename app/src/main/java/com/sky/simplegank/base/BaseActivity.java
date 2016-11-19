package com.sky.simplegank.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.bumptech.glide.Glide;
import com.sky.simplegank.App;
import com.sky.simplegank.R;

/**
 * Created by tonycheng on 2016/11/3.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private SVProgressHUD mProgressHUD;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getData();

        initDialog();
    }

    private void initDialog() {
        mProgressHUD = new SVProgressHUD(this);
    }

    public void showProgressDialog() {
        dismissProgressDialog();
        mProgressHUD.showWithStatus(getString(R.string.saving_image),
                SVProgressHUD.SVProgressHUDMaskType.Black);
    }

    public void showProgressDialog(String message) {
        if (TextUtils.isEmpty(message)) {
            dismissProgressDialog();
        } else {
            dismissProgressDialog();
            mProgressHUD.showWithStatus(message, SVProgressHUD.SVProgressHUDMaskType.Black);
        }
    }

    public void showProgressSuccess(String message) {
        dismissProgressDialog();
        mProgressHUD.showSuccessWithStatus(message, SVProgressHUD.SVProgressHUDMaskType.Black);
    }

    public void showProgressFail(String message) {
        dismissProgressDialog();
        mProgressHUD.showErrorWithStatus(message, SVProgressHUD.SVProgressHUDMaskType.Black);
    }

    public void dismissProgressDialog() {
        if (mProgressHUD.isShowing()) {
            mProgressHUD.dismiss();
        }
    }

    public abstract void getData();

    @Override
    protected void onDestroy() {
        dismissProgressDialog();
        Glide.with(App.getContext()).pauseRequests();
        super.onDestroy();
    }
}
