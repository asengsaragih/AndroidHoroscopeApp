package com.suncode.horoscope;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.suncode.horoscope.helper.ApiClient;
import com.suncode.horoscope.model.Horoscope;
import com.suncode.horoscope.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.suncode.horoscope.base.Constant.DAY_TODAY;
import static com.suncode.horoscope.base.Constant.DAY_TOMORROW;
import static com.suncode.horoscope.base.Constant.SIGN_SAGITTARIUS;

public class MainActivity extends AppCompatActivity {
    private ApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApiService = ApiClient.build().create(ApiService.class);

        getData();
    }

    private void getData() {
        Call<Horoscope> call = mApiService.getData(SIGN_SAGITTARIUS, DAY_TOMORROW);

        call.enqueue(new Callback<Horoscope>() {
            @Override
            public void onResponse(Call<Horoscope> call, Response<Horoscope> response) {
                Horoscope horoscope = response.body();

                Log.d("CHECKTAG", horoscope.getDateRange());
                Log.d("CHECKTAG", horoscope.getCurrentDate());
                Log.d("CHECKTAG", horoscope.getDescription());
                Log.d("CHECKTAG", horoscope.getCompatibility());
                Log.d("CHECKTAG", horoscope.getMood());
                Log.d("CHECKTAG", horoscope.getColor());
                Log.d("CHECKTAG", horoscope.getLuckyNumber());
                Log.d("CHECKTAG", horoscope.getLuckyTime());
            }

            @Override
            public void onFailure(Call<Horoscope> call, Throwable t) {

            }
        });
    }
}