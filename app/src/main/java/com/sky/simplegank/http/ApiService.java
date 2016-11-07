package com.sky.simplegank.http;

import com.sky.simplegank.entity.GankEntity;
import com.sky.simplegank.entity.VideoEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by tonycheng on 2016/10/14.
 */

public interface ApiService {

    //http://gank.io/api/data/Android/10/1
    @Headers("Cache-Control: public, max-age=3600")
    @GET("data/{category}/{count}/{page}")
    Observable<HttpResult<List<GankEntity>>> getGankList(
            @Path("category") String category,
            @Path("count") int count,
            @Path("page") int page);

    //http://gank.io/api/history/content/10/1
    @GET("history/content/{count}/{page}")
    Observable<HttpResult<List<VideoEntity>>> getVideoList(
            @Path("count") int count,
            @Path("page") int page);
}
