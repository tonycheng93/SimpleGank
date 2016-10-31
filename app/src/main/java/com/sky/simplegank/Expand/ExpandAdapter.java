package com.sky.simplegank.Expand;

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
 * Created by tonycheng on 2016/10/31.
 */

public class ExpandAdapter extends UltimateViewAdapter<ExpandAdapter.ExpandViewHolder> {

    private Context mContext;
    private List<GankEntity> mData;

    public ExpandAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<GankEntity> data) {
        mData = data;
        this.notifyDataSetChanged();
    }

    @Override
    public ExpandViewHolder onCreateViewHolder(ViewGroup parent) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expand, parent, false);
        return new ExpandViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ExpandViewHolder holder, int position) {
        GankEntity expand = mData.get(position);

        if (expand == null) {
            return;
        }

        String desc = expand.getDesc();
        String time = expand.getPublishedAt().split("T")[0];
        String who = expand.getWho();
        List<String> imageUrl = null;

        holder.tvExpand.setText(desc);

        if (expand.getImages() != null && expand.getImages().size() > 0) {
            imageUrl = expand.getImages();
        }

        if (imageUrl != null && imageUrl.size() > 0) {
            ImageLoader.display(mContext, imageUrl.get(0), holder.ivExpand);
        } else {
            ImageLoader.display(mContext, R.mipmap.ic_launcher, holder.ivExpand);
        }
    }

    @Override
    public int getAdapterItemCount() {
        return 0;
    }

    @Override
    public long generateHeaderId(int position) {
        return 0;
    }

    @Override
    public ExpandViewHolder newFooterHolder(View view) {
        return null;
    }

    @Override
    public ExpandViewHolder newHeaderHolder(View view) {
        return null;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public class ExpandViewHolder extends UltimateRecyclerviewViewHolder {

        private TextView tvExpand;
        private ImageView ivExpand;

        public ExpandViewHolder(View itemView) {
            super(itemView);
            tvExpand = (TextView) itemView.findViewById(R.id.tv_expand);
            ivExpand = (ImageView) itemView.findViewById(R.id.iv_expand);
        }
    }
}
