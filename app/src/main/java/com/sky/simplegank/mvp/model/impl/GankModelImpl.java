package com.sky.simplegank.mvp.model.impl;

import com.sky.simplegank.entity.GankEntity;
import com.sky.simplegank.http.ApiService;
import com.sky.simplegank.http.HttpResult;
import com.sky.simplegank.http.HttpResultSubscriber;
import com.sky.simplegank.http.RetrofitClient;
import com.sky.simplegank.mvp.model.IGankModel;
import com.sky.simplegank.utils.Debugger;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tonycheng on 2016/12/1.
 */

public class GankModelImpl implements IGankModel {

    @Override
    public void loadGank(String category, int count, int page, final OnLoadGankListener listener) {
        ApiService apiService = RetrofitClient.getInstance().getApiService();

        Observable<HttpResult<List<GankEntity>>> resultObservable = apiService.getGankList(category, count, page);
        resultObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpResultSubscriber<List<GankEntity>>() {
                    @Override
                    public void onSuccess(HttpResult<List<GankEntity>> result) {
                        Debugger.d(result.getResults().toString());
                        listener.onSuccess(result.getResults());
                    }

                    @Override
                    public void onFail(Throwable e) {
                        listener.onFail(e);
                    }
                });
    }

    public interface OnLoadGankListener {
        void onSuccess(List<GankEntity> entities);

        void onFail(Throwable throwable);
    }
}
