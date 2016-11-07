package com.sky.simplegank.Welfare.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import com.sky.simplegank.R;
import com.sky.simplegank.base.BaseActivity;
import com.sky.simplegank.utils.ImageLoader;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PictureActivity extends BaseActivity {

    private PhotoView ivWelfare;
    private FabSpeedDial fab;
    private String imageUrl;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        ivWelfare = (PhotoView) findViewById(R.id.iv_welfare);
        fab = (FabSpeedDial) findViewById(R.id.fab_speed_dial);

        if (!TextUtils.isEmpty(imageUrl)) {
            ImageLoader.display(this, imageUrl, ivWelfare);
        }

        PhotoViewAttacher attacher = new PhotoViewAttacher(ivWelfare, true);
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

        fab.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_save:
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
    }
}
