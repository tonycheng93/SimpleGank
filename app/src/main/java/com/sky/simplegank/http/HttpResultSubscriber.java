package com.sky.simplegank.http;

import com.sky.simplegank.utils.Debugger;

import rx.Subscriber;

/**
 * Created by tonycheng on 2016/10/14.
 */

public abstract class HttpResultSubscriber<T> extends
        Subscriber<HttpResult<T>> {
    protected HttpResultSubscriber() {
        super();
    }

    @Override
    public void onNext(HttpResult<T> tHttpResult) {
        if (!tHttpResult.isError()) {
            Debugger.d(tHttpResult.getResults().toString());
            onSuccess(tHttpResult);
        } else {
            onFail(new Throwable("error = " + tHttpResult.isError()));
        }
    }

    @Override
    public void onError(Throwable e) {
        onFail(e);
    }

    @Override
    public void onCompleted() {
        // TODO: 2016/10/14  onCompleted()方法中还不确定具体要封装些什么
    }

    public abstract void onSuccess(HttpResult<T> result);

    public abstract void onFail(Throwable e);
}
