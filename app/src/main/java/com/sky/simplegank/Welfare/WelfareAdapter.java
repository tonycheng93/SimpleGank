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
import com.sky.simplegank.R;
import com.sky.simplegank.entity.GankEntity;
import com.sky.simplegank.utils.DensityUtil;
import com.sky.simplegank.utils.ImageLoader;

import java.util.List;

/**
 * Created by tonycheng on 2016/10/17.
 */

public class WelfareAdapter extends RecyclerView.Adapter<WelfareAdapter.WelfareViewHolder> {

    private static final String TAG = "WelfareAdapter";

    private Context mContext;
    private List<GankEntity> mData;

    private int mScreenWidth;

    public WelfareAdapter(Context context) {
        this.mContext = context;
        mScreenWidth = DensityUtil.getWidthInPx(context);
    }

    public void setData(List<GankEntity> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }

    @Override
    public WelfareViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_welfare, parent, false);
        return new WelfareViewHolder(rootView);
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
                //计算宽高比
                int finalHeight = (mScreenWidth / 2) * height / width;
                if (welfare.getItemHeight() <= 0) {
                    welfare.setItemHeight(finalHeight);
                    ViewGroup.LayoutParams params = holder.cdRoot.getLayoutParams();
                    params.height = welfare.getItemHeight();
                }
                holder.ivWelfare.setImageBitmap(resource);
            }
        });
//        ImageLoader.display(mContext, welfare.getUrl(), holder.ivWelfare);
    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    public class WelfareViewHolder extends RecyclerView.ViewHolder {

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
