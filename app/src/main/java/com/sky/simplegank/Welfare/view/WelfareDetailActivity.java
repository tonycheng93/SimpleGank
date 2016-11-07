package com.sky.simplegank.Welfare.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
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
        setContentView(R.layout.activity_picture);

        ivWelfare = (PhotoView) findViewById(R.id.iv_welfare);
        fab = (FabSpeedDial) findViewById(R.id.fab_speed_dial);

        mPresenter = new WelfareDetailPresenterImpl(this);

        final PhotoViewAttacher attacher = new PhotoViewAttacher(ivWelfare, true);
        attacher.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float x, float y) {
                view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        return false;
                    }
                });
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

    }


    @Override
    public void showSuccessMsg() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showFailMsg() {

    }
}
