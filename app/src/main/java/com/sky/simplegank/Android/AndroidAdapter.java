package com.sky.simplegank.Android;

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
 * Created by tonycheng on 2016/10/25.
 */

public class AndroidAdapter extends UltimateViewAdapter<AndroidAdapter.AndroidViewHolder> {


    private Context mContext;
    private List<GankEntity> mData;

    public AndroidAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<GankEntity> data) {
        mData = data;
        this.notifyDataSetChanged();
    }

    @Override
    public AndroidViewHolder onCreateViewHolder(ViewGroup parent) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_android, parent, false);
        return new AndroidViewHolder(rootView);
    }

    @Override
    public int getAdapterItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public void onBindViewHolder(AndroidViewHolder holder, int position) {
        GankEntity android = mData.get(position);
        if (android == null) {
            return;
        }

        String desc = android.getDesc();
        String time = android.getPublishedAt().split("T")[0];
        String who = android.getWho();
        String url = android.getUrl();
        List<String> imageUrl = null;

        //这里需要注意的一点是，images这一项是可能没有值的，所以需要判断
//        String androidStr = android.toString();
//        try {
//            JSONObject jsonObject = new JSONObject(androidStr);
//            if (jsonObject.has("images")){
//                imageUrl = android.getImages();
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        if (android.getImages() != null && android.getImages().size() > 0) {
            imageUrl = android.getImages();
        }

        holder.tvAndroid.setText(desc);

        if (imageUrl != null && imageUrl.size() > 0) {
            ImageLoader.display(mContext, imageUrl.get(0), holder.ivAndroid);
        } else {
            ImageLoader.display(mContext, R.mipmap.ic_launcher, holder.ivAndroid);
        }
    }

    @Override
    public AndroidViewHolder newFooterHolder(View view) {
        return null;
    }

    @Override
    public AndroidViewHolder newHeaderHolder(View view) {
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

    public class AndroidViewHolder extends UltimateRecyclerviewViewHolder {

        private TextView tvAndroid;
        private ImageView ivAndroid;

        public AndroidViewHolder(View itemView) {
            super(itemView);

            tvAndroid = (TextView) itemView.findViewById(R.id.tv_android);
            ivAndroid = (ImageView) itemView.findViewById(R.id.iv_android);
        }
    }
}
