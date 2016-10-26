package com.sky.simplegank.Android.model.impl;

import com.sky.simplegank.Android.model.IAndroidModel;
import com.sky.simplegank.config.Constant;
import com.sky.simplegank.entity.GankEntity;
import com.sky.simplegank.http.ApiService;
import com.sky.simplegank.http.HttpResult;
import com.sky.simplegank.http.HttpResultSubscriber;
import com.sky.simplegank.http.RetrofitClient;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tonycheng on 2016/10/25.
 */

public class AndroidModelImpl implements IAndroidModel {

    @Override
    public void loadAndroidList(int count, int page, final OnLoadAndroidListListener listener) {
        ApiService apiService = RetrofitClient.getInstance().getApiService();

        Observable<HttpResult<List<GankEntity>>> androidList = apiService.getGankList(Constant.CATEGORY_ANDROID, count, page);
        androidList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpResultSubscriber<List<GankEntity>>() {
                    @Override
                    public void onSuccess(HttpResult<List<GankEntity>> result) {
                        listener.onSuccess(result.getResults());
                    }

                    @Override
                    public void onFail(Throwable e) {
                        listener.onFail(e);
                    }
                });
    }

    public interface OnLoadAndroidListListener {
        void onSuccess(List<GankEntity> androidList);

        void onFail(Throwable e);
    }
}
