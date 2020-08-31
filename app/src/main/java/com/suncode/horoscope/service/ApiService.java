package com.suncode.horoscope.service;

import com.suncode.horoscope.model.Horoscope;
import com.suncode.horoscope.model.Image;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @POST("/")
    public Call<Horoscope> getData(@Query("sign") String sign, @Query("day") String day);

    @GET("/asengsaragih/public-image/master/horoscope.json")
    public Call<Image> getImage();
}
