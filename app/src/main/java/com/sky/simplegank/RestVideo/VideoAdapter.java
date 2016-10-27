package com.sky.simplegank.RestVideo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.sky.simplegank.R;
import com.sky.simplegank.entity.VideoEntity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * Created by tonycheng on 2016/10/26.
 */

public class VideoAdapter extends UltimateViewAdapter<VideoAdapter.VideoViewHolder> {

    private List<VideoEntity> mData;
    private Context mContext;

    public VideoAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<VideoEntity> data) {
        mData = data;
        this.notifyDataSetChanged();
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reset_video, parent, false);
        return new VideoViewHolder(rootView);
    }

    @Override
    public int getAdapterItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        VideoEntity video = mData.get(position);

        String content = video.getContent();
        //解析网页获得视频地址和标题
        Document document = Jsoup.parse(content);
        Elements elements = document.select("iframe");
        String videoUrl = elements.attr("src");
    }

    @Override
    public long generateHeaderId(int position) {
        return 0;
    }

    @Override
    public VideoViewHolder newFooterHolder(View view) {
        return null;
    }

    @Override
    public VideoViewHolder newHeaderHolder(View view) {
        return null;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public class VideoViewHolder extends UltimateRecyclerviewViewHolder {

        private TextView tvVideo;
        private VideoView vvVideo;

        public VideoViewHolder(View itemView) {
            super(itemView);
            tvVideo = (TextView) itemView.findViewById(R.id.tv_video);
            vvVideo = (VideoView) itemView.findViewById(R.id.vv_video);

        }
    }
}
