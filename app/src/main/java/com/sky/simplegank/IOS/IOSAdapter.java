package com.sky.simplegank.IOS;

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
 * 创建时间：2016/10/30 11:35
 * 邮箱：tonycheng93@outlook.com
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class IOSAdapter extends UltimateViewAdapter<IOSAdapter.IOSViewHolder> {

    private Context mContext;
    private List<GankEntity> mData;

    public IOSAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<GankEntity> data) {
        mData = data;
        this.notifyDataSetChanged();
    }

    @Override
    public IOSViewHolder onCreateViewHolder(ViewGroup parent) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ios,
                parent, false);
        return new IOSViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(IOSViewHolder holder, int position) {
        GankEntity iOS = mData.get(position);
        if (iOS == null) {
            return;
        }

        String desc = iOS.getDesc();
        String time = iOS.getPublishedAt().split("T")[0];
        String who = iOS.getWho();
        String url = iOS.getUrl();
        List<String> imageUrl = null;

        if (iOS.getImages() != null && iOS.getImages().size() > 0) {
            imageUrl = iOS.getImages();
        }

        holder.tvIOS.setText(desc);
        if (imageUrl != null && imageUrl.size() > 0) {
            ImageLoader.display(mContext, imageUrl.get(0), holder.ivIOS);
        } else {
            holder.ivIOS.setVisibility(View.GONE);
//            ImageLoader.display(mContext, R.mipmap.ic_launcher, holder.ivIOS);
        }
    }

    @Override
    public int getAdapterItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public IOSViewHolder newFooterHolder(View view) {
        return null;
    }

    @Override
    public IOSViewHolder newHeaderHolder(View view) {
        return null;
    }

    @Override
    public long generateHeaderId(int i) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

    }

    public class IOSViewHolder extends UltimateRecyclerviewViewHolder {
        private TextView tvIOS;
        private ImageView ivIOS;

        public IOSViewHolder(View itemView) {
            super(itemView);
            tvIOS = (TextView) itemView.findViewById(R.id.tv_ios);
            ivIOS = (ImageView) itemView.findViewById(R.id.iv_ios);
        }
    }
}
