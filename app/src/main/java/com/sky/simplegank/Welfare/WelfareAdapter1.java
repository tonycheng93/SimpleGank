package com.sky.simplegank.Welfare;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.sky.simplegank.R;
import com.sky.simplegank.entity.GankEntity;
import com.sky.simplegank.utils.Debugger;
import com.sky.simplegank.utils.DensityUtil;
import com.sky.simplegank.utils.ImageLoader;

import java.util.List;

/**
 * Created by tonycheng on 2016/10/24.
 */

public class WelfareAdapter1 extends UltimateViewAdapter<WelfareAdapter1.WelfareViewHolder> {

    private static final String TAG = "WelfareAdapter";

    private Context mContext;
    private List<GankEntity> mData;

    private int mScreenWidth;

    public WelfareAdapter1(Context context) {
        this.mContext = context;
        mScreenWidth = DensityUtil.getWidthInPx(context);
        Debugger.d(TAG,"屏幕的宽度：" + mScreenWidth);
    }

    public void setData(List<GankEntity> data) {
        mData = data;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public WelfareViewHolder newFooterHolder(View view) {
        return null;
    }

    @Override
    public WelfareViewHolder newHeaderHolder(View view) {
        return null;
    }

    @Override
    public long generateHeaderId(int position) {
        return 0;
    }

    @Override
    public WelfareViewHolder onCreateViewHolder(ViewGroup parent) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_welfare, parent, false);
        return new WelfareViewHolder(rootView);
    }

    @Override
    public int getAdapterItemCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    @Override
    public void onBindViewHolder(final WelfareViewHolder holder, int position) {
        final GankEntity welfare = mData.get(position);
        if (welfare == null) {
            return;
        }

        String time = welfare.getPublishedAt().split("T")[0];
        holder.tvWelfare.setText(time);

        if (welfare.getItemHeight() > 0) {
            ViewGroup.LayoutParams params = holder.cdRoot.getLayoutParams();
            params.height = welfare.getItemHeight();
        }

        ImageLoader.display(mContext, welfare.getUrl(), new SimpleTarget<Bitmap>(mScreenWidth / 2, mScreenWidth / 2) {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                int width = resource.getWidth();
                int height = resource.getHeight();
                Debugger.d(TAG,"图片的width = " + width + ",height = " + height);
                //计算宽高比
                int finalHeight;
                if (width > height){//尽量保持图片处于一个正常的宽高比率
                    finalHeight = (mScreenWidth /2) * width / height;
                }else {
                    finalHeight = (mScreenWidth / 2) * height / width;
                }
                Debugger.d(TAG,"计算出的item的高度：" + finalHeight);
                if (welfare.getItemHeight() <= 0) {
                    welfare.setItemHeight(finalHeight);
                    ViewGroup.LayoutParams params = holder.cdRoot.getLayoutParams();
                    params.height = welfare.getItemHeight();
                }
                holder.ivWelfare.setTag(welfare.getUrl());
                if (holder.ivWelfare.getTag().equals(welfare.getUrl())) {
                    holder.ivWelfare.setImageBitmap(resource);
                }
            }
        });
    }


    public class WelfareViewHolder extends UltimateRecyclerviewViewHolder {

        private CardView cdRoot;
        private ImageView ivWelfare;
        private TextView tvWelfare;

        public WelfareViewHolder(View itemView) {
            super(itemView);

            cdRoot = (CardView) itemView.findViewById(R.id.card_view);
            ivWelfare = (ImageView) itemView.findViewById(R.id.iv_welfare);
            tvWelfare = (TextView) itemView.findViewById(R.id.tv_welfare);
        }
    }
}
