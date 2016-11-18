package com.sky.simplegank.Welfare.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.sky.simplegank.R;
import com.sky.simplegank.Welfare.presenter.IWelfareDetailPresenter;
import com.sky.simplegank.Welfare.presenter.impl.WelfareDetailPresenterImpl;
import com.sky.simplegank.base.BaseActivity;
import com.sky.simplegank.utils.ImageLoader;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class WelfareDetailActivity extends BaseActivity implements IWelfareDetailView {

    private PhotoView ivWelfare;
    private FabSpeedDial fab;
    private String imageUrl;
    private String imageTime;
    private Bitmap mBitmap;

    private IWelfareDetailPresenter mPresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welfare_detail);

        ivWelfare = (PhotoView) findViewById(R.id.iv_welfare);
        fab = (FabSpeedDial) findViewById(R.id.fab_speed_dial);

        mPresenter = new WelfareDetailPresenterImpl(this);

        final PhotoViewAttacher attacher = new PhotoViewAttacher(ivWelfare, true);
        attacher.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float x, float y) {

            }
        });

        attacher.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(WelfareDetailActivity.this)
                        .setMessage(R.string.save_to_phone)
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mPresenter.saveImage(mBitmap, imageTime);
                                dialog.dismiss();
                            }
                        })
                        .show();
                return true;
            }
        });

        if (!TextUtils.isEmpty(imageUrl)) {
            ImageLoader.display(this, imageUrl, new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    ivWelfare.setImageBitmap(resource);
                    attacher.update();
                    mBitmap = resource;
                }
            });
        }


        fab.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_save:
                        if (imageUrl != null) {
                            mPresenter.saveImage(mBitmap, imageTime);
                        }
                        break;
                    case R.id.action_share:
                        break;
                    case R.id.action_wallpaper:
                        mPresenter.setWallPaper(mBitmap);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void getData() {
        Intent intent = getIntent();
        imageUrl = intent.getStringExtra(WelfareFragment.PICTURE_URL_FLAG);
        imageTime = intent.getStringExtra(WelfareFragment.PICTURE_TIME_FLAG);
    }

    @Override
    public void showProgress() {
        showProgressDialog();
    }


    @Override
    public void showSuccessMsg() {
        showProgressSuccess(getString(R.string.saving_success));//调用了这个方法
    }

    @Override
    public void hideProgress() {
        dismissProgressDialog();
    }

    @Override
    public void showFailMsg() {
        showProgressFail(getString(R.string.saving_failed));
    }
}
