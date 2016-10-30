package com.sky.simplegank.FrontEnd;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
 * 项目名称：SimpleGank
 * 类描述：
 * 创建人：tonycheng
 * 创建时间：2016/10/30 21:21
 * 邮箱：tonycheng93@outlook.com
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class FrontEndAdapter extends UltimateViewAdapter<FrontEndAdapter.FrontEndViewHolder> {

    private Context mContext;
    private List<GankEntity> mData;

    public FrontEndAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<GankEntity> data) {
        mData = data;
        this.notifyDataSetChanged();
    }

    @Override
    public FrontEndViewHolder onCreateViewHolder(ViewGroup parent) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_front_end,
                parent, false);
        return new FrontEndViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(FrontEndViewHolder holder, int position) {
        GankEntity frontEnd = mData.get(position);
        if (frontEnd == null) {
            return;
        }
        String desc = frontEnd.getDesc();
        String time = frontEnd.getPublishedAt().split("T")[0];
        String who = frontEnd.getWho();
        List<String> imageUrl = null;

        if (frontEnd.getImages() != null && frontEnd.getImages().size() > 0) {
            imageUrl = frontEnd.getImages();
        }

        holder.tvFrontEnd.setText(desc);
        if (imageUrl != null && imageUrl.size() > 0) {
            ImageLoader.display(mContext, imageUrl.get(0), holder.ivFrontView);
        } else {
            ImageLoader.display(mContext, R.mipmap.ic_launcher, holder.ivFrontView);
        }
    }

    @Override
    public int getAdapterItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public FrontEndViewHolder newFooterHolder(View view) {
        return null;
    }

    @Override
    public FrontEndViewHolder newHeaderHolder(View view) {
        return null;
    }

    @Override
    public long generateHeaderId(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public class FrontEndViewHolder extends UltimateRecyclerviewViewHolder {

        private TextView tvFrontEnd;
        private ImageView ivFrontView;

        public FrontEndViewHolder(View itemView) {
            super(itemView);
            tvFrontEnd = (TextView) itemView.findViewById(R.id.tv_front_end);
            ivFrontView = (ImageView) itemView.findViewById(R.id.iv_front_end);
        }
    }
}
