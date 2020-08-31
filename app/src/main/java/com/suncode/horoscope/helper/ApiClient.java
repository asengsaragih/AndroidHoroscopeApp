package com.suncode.horoscope.helper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.suncode.horoscope.base.Constant.BASE_URL_HOROSCOPE;
import static com.suncode.horoscope.base.Constant.BASE_URL_IMAGE;

public class ApiClient {
    public static Retrofit horoscopeBuild() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL_HOROSCOPE)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        return retrofit;
    }

    public static Retrofit imageBuild() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL_IMAGE)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        return retrofit;
    }
}
