package com.sky.simplegank.Expand.model.impl;

import com.sky.simplegank.Expand.model.IExpandModel;
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
 * Created by tonycheng on 2016/10/31.
 */

public class ExpandModelImpl implements IExpandModel {

    @Override
    public void loadExpandList(int count, int page, final OnLoadExpandListListener listener) {
        ApiService apiService = RetrofitClient.getInstance().getApiService();

        Observable<HttpResult<List<GankEntity>>> expand = apiService.getGankList(Constant.CATEGORY_EXPAND_RESOURCES, count, page);
        expand.subscribeOn(Schedulers.io())
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

    public interface OnLoadExpandListListener {
        void onSuccess(List<GankEntity> expandList);

        void onFail(Throwable e);
    }
}
