package com.sky.simplegank.FrontEnd.model.impl;

import com.sky.simplegank.FrontEnd.model.IFrontEndModel;
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
 * 创建时间：2016/10/30 21:34
 * 邮箱：tonycheng93@outlook.com
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class FrontEndModelImpl implements IFrontEndModel {

    @Override
    public void loadFrontEndList(int count, int page, final OnLoadFrontEndListListener listener) {
        ApiService apiService = RetrofitClient.getInstance().getApiService();

        Observable<HttpResult<List<GankEntity>>> frontEndList = apiService.getGankList(Constant
                .CATEGORY_FRONT_END, count, page);
        frontEndList.subscribeOn(Schedulers.io())
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

    public interface OnLoadFrontEndListListener {
        void onSuccess(List<GankEntity> frontEndList);

        void onFail(Throwable e);
    }
}
