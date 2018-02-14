package com.amit.woohoo.rest;

import android.net.Uri;

import com.amit.woohoo.R;

import java.net.URLEncoder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by readyassist on 2/14/18.
 */

public class ApiClient {

    private static final String BASE_URL = "https://query.yahooapis.com";

    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
