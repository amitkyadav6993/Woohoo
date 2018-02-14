package com.amit.woohoo.rest;

import com.amit.woohoo.models.ForcastResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by readyassist on 2/14/18.
 */

public interface ApiInterface {

    @GET("/v1/public/yql/")
    Call<ForcastResult> getForcastResult(@Query("q") String q,@Query("format") String format,@Query("env") String env);
}
