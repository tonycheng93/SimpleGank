package com.sky.simplegank.IOS.model.impl;

import com.sky.simplegank.IOS.model.IIOSModel;
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
 * 项目名称：SimpleGank
 * 类描述：
 * 创建人：tonycheng
 * 创建时间：2016/10/30 14:34
 * 邮箱：tonycheng93@outlook.com
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class IOSModelImpl implements IIOSModel {

    @Override
    public void onLoadIOSList(int count, int page, final OnLoadIOSListListener listener) {
        ApiService apiService = RetrofitClient.getInstance().getApiService();

        Observable<HttpResult<List<GankEntity>>> iOSList = apiService.getGankList(Constant
                .CATEGORY_IOS, count, page);
        iOSList.subscribeOn(Schedulers.io())
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

    public interface OnLoadIOSListListener {
        void onSuccess(List<GankEntity> iOSList);

        void onFail(Throwable e);
    }
}
