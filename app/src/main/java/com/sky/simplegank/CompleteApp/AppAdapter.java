package com.sky.simplegank.CompleteApp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.sky.simplegank.R;
import com.sky.simplegank.entity.GankEntity;
import com.sky.simplegank.utils.ImageLoader;

import java.util.List;

/**
 * Created by tonycheng on 2016/10/31.
 */

public class AppAdapter extends UltimateViewAdapter<AppAdapter.AppViewHolder> {

    private Context mContext;
    private List<GankEntity> mData;

    public AppAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<GankEntity> data) {
        mData = data;
        this.notifyDataSetChanged();
    }

    @Override
    public AppViewHolder onCreateViewHolder(ViewGroup parent) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_app, parent, false);
        return new AppViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(AppViewHolder holder, int position) {
        GankEntity app = mData.get(position);
        if (app == null) {
            return;
        }

        String desc = app.getDesc();
        String time = app.getPublishedAt().split("T")[0];
        String who = app.getWho();
        List<String> imageUrl = null;

        if (!TextUtils.isEmpty(desc)) {
            holder.tvApp.setText(desc);
        }

        if (app.getImages() != null && app.getImages().size() > 0) {
            imageUrl = app.getImages();
        }

        if (imageUrl != null && imageUrl.size() > 0) {
            ImageLoader.display(mContext, imageUrl.get(0), holder.ivApp);
        } else {
            holder.ivApp.setVisibility(View.GONE);
//            ImageLoader.display(mContext, R.mipmap.ic_launcher, holder.ivApp);
        }
    }

    @Override
    public int getAdapterItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public long generateHeaderId(int position) {
        return 0;
    }

    @Override
    public AppViewHolder newFooterHolder(View view) {
        return null;
    }

    @Override
    public AppViewHolder newHeaderHolder(View view) {
        return null;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public class AppViewHolder extends UltimateRecyclerviewViewHolder {

        private TextView tvApp;
        private ImageView ivApp;

        public AppViewHolder(View itemView) {
            super(itemView);
            tvApp = (TextView) itemView.findViewById(R.id.tv_app);
            ivApp = (ImageView) itemView.findViewById(R.id.iv_app);
        }
    }
}
