package com.sky.simplegank.CompleteApp.model.impl;

import com.sky.simplegank.CompleteApp.model.IAppModel;
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

public class AppModelImpl implements IAppModel {

    @Override
    public void loadAppList(int count, int page, final OnLoadAppListListener listener) {
        ApiService apiService = RetrofitClient.getInstance().getApiService();

        Observable<HttpResult<List<GankEntity>>> appList = apiService.getGankList(Constant.CATEGORY_APP, count, page);
        appList.subscribeOn(Schedulers.io())
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

    public interface OnLoadAppListListener {
        void onSuccess(List<GankEntity> appList);

        void onFail(Throwable e);
    }
}
