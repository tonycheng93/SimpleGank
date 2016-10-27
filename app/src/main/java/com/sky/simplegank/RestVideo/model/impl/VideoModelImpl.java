package com.sky.simplegank.RestVideo.model.impl;

import com.sky.simplegank.RestVideo.model.IVideoModel;
import com.sky.simplegank.entity.VideoEntity;
import com.sky.simplegank.http.ApiService;
import com.sky.simplegank.http.HttpResult;
import com.sky.simplegank.http.HttpResultSubscriber;
import com.sky.simplegank.http.RetrofitClient;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tonycheng on 2016/10/26.
 */

public class VideoModelImpl implements IVideoModel {

    @Override
    public void loadVideoList(int count, int page, final OnLoadVideoListListener listener) {
        ApiService apiService = RetrofitClient.getInstance().getApiService();

        Observable<HttpResult<List<VideoEntity>>> videoList = apiService.getVideoList(count, page);
        videoList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpResultSubscriber<List<VideoEntity>>() {
                    @Override
                    public void onSuccess(HttpResult<List<VideoEntity>> result) {
                        listener.onSuccess(result.getResults());
                    }

                    @Override
                    public void onFail(Throwable e) {
                        listener.onFail(e);
                    }
                });
    }

    public interface OnLoadVideoListListener {
        void onSuccess(List<VideoEntity> videoList);

        void onFail(Throwable e);
    }
}
