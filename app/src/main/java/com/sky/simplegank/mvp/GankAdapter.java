package com.sky.simplegank.mvp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sky.simplegank.R;
import com.sky.simplegank.entity.GankEntity;
import com.sky.simplegank.utils.ImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tonycheng on 2016/12/1.
 */

public class GankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_PLAIN_TEXT = 0;
    private static final int TYPE_TEXT_AND_IMAGE = 1;
    private static final int TYPE_FOOTER = 3;

    private Context mContext;
    private List<GankEntity> mData;
    private LayoutInflater mInflater;
    private boolean isShowFooter;
    private OnItemClickListener mOnItemClickListener;

    public GankAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setData(List<GankEntity> data) {
        mData = data;
        int positionStart = mData.size();
        notifyItemRangeInserted(positionStart,data.size());
    }

    public boolean isShowFooter() {
        return isShowFooter;
    }

    public void setShowFooter(boolean showFooter) {
        isShowFooter = showFooter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView;
        switch (viewType) {
            case TYPE_PLAIN_TEXT:
                rootView = mInflater.inflate(R.layout.item_gank_with_text, parent, false);
                return new TextViewHolder(rootView);
            case TYPE_TEXT_AND_IMAGE:
                rootView = mInflater.inflate(R.layout.item_gank_with_text_and_image, parent, false);
                return new ImageViewHolder(rootView);
            case TYPE_FOOTER:
                rootView = mInflater.inflate(R.layout.view_footer, parent, false);
                return new FooterViewHolder(rootView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);

        if (viewType != TYPE_FOOTER) {
            GankEntity entity = mData.get(position);
            TextViewHolder textViewHolder = (TextViewHolder) holder;
            textViewHolder.mTitle.setText(entity.getDesc());
            if (entity.getWho() != null) {
                textViewHolder.mAuthor.setText(String.format("via %s", entity.getWho()));
            } else {
                textViewHolder.mAuthor.setText("");
            }
            textViewHolder.mTime.setText(entity.getPublishedAt().split("T")[0]);
            textViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(v, position);
                    }
                }
            });
            if (viewType == TYPE_TEXT_AND_IMAGE) {
                ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
                imageViewHolder.mBanner.setImages(entity.getImages())
                        .setImageLoader(new ImageLoader())
                        .setBannerAnimation(Transformer.DepthPage)
                        .start();
            }
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : (mData.size() + (isShowFooter() ? 1 : 0));
    }

    @Override
    public int getItemViewType(int position) {
        if (isShowFooter() && position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        }
        GankEntity entity = mData.get(position);
        if (entity.getImages() == null || entity.getImages().size() == 0) {
            return TYPE_PLAIN_TEXT;
        } else {
            return TYPE_TEXT_AND_IMAGE;
        }
    }

    static class TextViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title_text_view)
        TextView mTitle;
        @BindView(R.id.author_text_view)
        TextView mAuthor;
        @BindView(R.id.time_text_view)
        TextView mTime;

        public TextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class ImageViewHolder extends TextViewHolder {
        @BindView(R.id.img_banner)
        Banner mBanner;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
