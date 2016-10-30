package com.sky.simplegank.IOS;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

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

public class IOSAdapter extends UltimateViewAdapter<IOSAdapter.IOSViewHolder>{

    @Override
    public IOSViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        return null;
    }

    @Override
    public void onBindViewHolder(IOSViewHolder holder, int position) {

    }

    @Override
    public int getAdapterItemCount() {
        return 0;
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

    public class IOSViewHolder extends UltimateRecyclerviewViewHolder{

        public IOSViewHolder(View itemView) {
            super(itemView);
        }
    }
}
