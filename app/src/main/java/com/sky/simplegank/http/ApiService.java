package com.sky.simplegank.http;

import com.sky.simplegank.entity.GankEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by tonycheng on 2016/10/14.
 */

public interface ApiService {

    //http://gank.io/api/data/Android/10/1
    @GET("data/{category}/{count}/{page}")
    Observable<HttpResult<List<GankEntity>>> getGankList(
            @Path("category") String category,
            @Path("count") int count,
            @Path("page") int page);
}
