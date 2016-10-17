package com.sky.simplegank.Welfare.model.impl;

import com.sky.simplegank.Welfare.model.IWelfareModel;
import com.sky.simplegank.config.Constant;
import com.sky.simplegank.entity.GankEntity;
import com.sky.simplegank.http.ApiService;
import com.sky.simplegank.http.HttpResult;
import com.sky.simplegank.http.HttpResultSubscriber;
import com.sky.simplegank.http.RetrofitClient;
import com.sky.simplegank.utils.Debugger;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tonycheng on 2016/10/17.
 */

public class WelfareModelImpl implements IWelfareModel {

    private static final String TAG = "WelfareModelImpl";

    @Override
    public void loadWelfareList(int count, int page, final OnLoadWelfareListListener listener) {
        ApiService apiService = RetrofitClient.getInstance().getApiService();

        Observable<HttpResult<List<GankEntity>>> welfareList = apiService.getGankList(Constant.CATEGORY_WELFARE, count, page);
        welfareList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpResultSubscriber<List<GankEntity>>() {
                    @Override
                    public void onSuccess(HttpResult<List<GankEntity>> result) {
                        Debugger.d(TAG,result.toString());
                        listener.onSuccess(result.getResults());
                    }

                    @Override
                    public void onFail(Throwable e) {
                        listener.onFail(e);
                    }
                });
    }

    public interface OnLoadWelfareListListener {
        void onSuccess(List<GankEntity> welfareList);

        void onFail(Throwable e);
    }
}
