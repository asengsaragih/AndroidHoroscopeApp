package com.suncode.horoscope;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.suncode.horoscope.helper.ApiClient;
import com.suncode.horoscope.model.Horoscope;
import com.suncode.horoscope.service.ApiService;

import retrofit2.Call;

public class DetailActivity extends AppCompatActivity {

    public static final String TAG = "CHECKTAG";
    private ApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //hide action bar
        getSupportActionBar().hide();
        //transparent status/notification bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //change color text in status/notification bar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        mApiService = ApiClient.horoscopeBuild().create(ApiService.class);

        getData();
    }

    private String getHoroscopeName() {
        Intent intent = getIntent();
        return intent.getStringExtra("HOROSCOPE_NAME");
    }

    private void getData() {
//        Call<Horoscope> horoscopeCall = mApiService.getData()
    }
}