package com.suncode.horoscope.service;

import com.suncode.horoscope.model.Horoscope;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @POST("/")
    public Call<Horoscope> getData(@Query("sign") String sign, @Query("day") String day);
}
