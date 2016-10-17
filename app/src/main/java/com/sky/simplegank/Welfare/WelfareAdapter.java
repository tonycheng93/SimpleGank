package com.sky.simplegank.Welfare;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sky.simplegank.R;
import com.sky.simplegank.entity.GankEntity;
import com.sky.simplegank.utils.ImageLoader;

import java.util.List;

/**
 * Created by tonycheng on 2016/10/17.
 */

public class WelfareAdapter extends RecyclerView.Adapter<WelfareAdapter.WelfareViewHolder> {

    private Context mContext;
    private List<GankEntity> mData;

    public WelfareAdapter(Context context) {
        this.mContext = context;
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
    public void onBindViewHolder(WelfareViewHolder holder, int position) {
        GankEntity welfare = mData.get(position);
        if (welfare != null) {
            ImageLoader.display(mContext, welfare.getUrl(), holder.ivWelfare);
            holder.tvWelfare.setText(welfare.getDesc());
        }
    }

    @Override
    public int getItemCount() {
        return mData.size() == 0 ? 0 : mData.size();
    }

    public class WelfareViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivWelfare;
        private TextView tvWelfare;

        public WelfareViewHolder(View itemView) {
            super(itemView);

            ivWelfare = (ImageView) itemView.findViewById(R.id.iv_welfare);
            tvWelfare = (TextView) itemView.findViewById(R.id.tv_welfare);
        }
    }
}
