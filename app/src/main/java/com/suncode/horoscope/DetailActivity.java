package com.suncode.horoscope;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.suncode.horoscope.base.Constant;
import com.suncode.horoscope.helper.ApiClient;
import com.suncode.horoscope.model.Horoscope;
import com.suncode.horoscope.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    public static final String TAG = "CHECKTAG";
    private ApiService mApiService;
    private ShimmerFrameLayout mDetailShimmer;

    private ImageView mBackButtonImageView;

    private Chip mTodayChip;
    private Chip mTommorowChip;
    private Chip mYesterdayChip;

    private ChipGroup mDayChipGroup;

    private TextView mTitleDetail;
    private TextView mIntervalDetail;
    private TextView mDescDetail;
    private TextView mCompatibilityDetail;
    private TextView mMoodDetail;
    private TextView mNumberDetail;
    private TextView mTimeDetail;
    private TextView mColorDetail;


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

        mDetailShimmer = findViewById(R.id.shimmer_detail);

        //chipgroup initilize
        mDayChipGroup = findViewById(R.id.chipgroup_detail_days);

        //chip initialize
        mTodayChip = findViewById(R.id.chip_detail_today);
        mTommorowChip = findViewById(R.id.chip_detail_tommorow);
        mYesterdayChip = findViewById(R.id.chip_detail_yesterday);

        //textview initilize
        mTitleDetail = findViewById(R.id.textView_detail_title);
        mIntervalDetail = findViewById(R.id.textView_detail_interval);
        mDescDetail = findViewById(R.id.textView_detail_content);
        mCompatibilityDetail = findViewById(R.id.textView_detail_compatibility);
        mMoodDetail = findViewById(R.id.textView_detail_mood);
        mNumberDetail = findViewById(R.id.textView_detail_luckynumber);
        mTimeDetail = findViewById(R.id.textView_detail_luckytime);
        mColorDetail = findViewById(R.id.textView_detail_color);

        //get data first load
        getData(Constant.DAY_TODAY);

        chipClicked();

        //for back button
        mBackButtonImageView = findViewById(R.id.imagebutton_detail_back);
        mBackButtonImageView.setOnClickListener(v -> finish());
    }

    private void chipClicked() {
        //function for chip clicked
        mYesterdayChip.setOnClickListener(v -> {
            chipChangeColor(mYesterdayChip, true);
            chipChangeColor(mTodayChip, false);
            chipChangeColor(mTommorowChip, false);

            getData(Constant.DAY_YESTERDAY);
        });

        mTodayChip.setOnClickListener(v -> {
            chipChangeColor(mYesterdayChip, false);
            chipChangeColor(mTodayChip, true);
            chipChangeColor(mTommorowChip, false);

            getData(Constant.DAY_TODAY);
        });

        mTommorowChip.setOnClickListener(v -> {
            chipChangeColor(mYesterdayChip, false);
            chipChangeColor(mTodayChip, false);
            chipChangeColor(mTommorowChip, true);

            getData(Constant.DAY_TOMORROW);
        });
    }

    private void chipChangeColor(Chip chip, Boolean b) {
        if (b) {
            chip.setChipBackgroundColorResource(android.R.color.white);
            chip.setChipStrokeColorResource(R.color.colorThree);
            chip.setChipStrokeWidth(1);
            chip.setTextColor(getResources().getColor(R.color.colorThree));
        } else {
            chip.setChipBackgroundColorResource(R.color.colorThree);
            chip.setChipStrokeWidth(0);
            chip.setTextColor(getResources().getColor(android.R.color.white));
        }
    }

    private float dpToFloat(int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    private String getHoroscopeName() {
        Intent intent = getIntent();
        return intent.getStringExtra("HOROSCOPE_NAME");
    }

    private void getData(String day) {
        //clear textview before adding new data
        mTitleDetail.setText("");
        mIntervalDetail.setText("");
        mDescDetail.setText("");
        mCompatibilityDetail.setText("");
        mMoodDetail.setText("");
        mNumberDetail.setText("");
        mTimeDetail.setText("");
        mColorDetail.setText("");

        //showing shimmer
        showShimmer(true);

        Call<Horoscope> horoscopeCall = mApiService.getData(getHoroscopeName(), day);

        horoscopeCall.enqueue(new Callback<Horoscope>() {
            @Override
            public void onResponse(Call<Horoscope> call, Response<Horoscope> response) {
                Horoscope horoscope = response.body();

                //set textview data
                mTitleDetail.setText(getHoroscopeName());
                mIntervalDetail.setText(horoscope.getDateRange());
                mDescDetail.setText(horoscope.getDescription());
                mCompatibilityDetail.setText(horoscope.getCompatibility());
                mMoodDetail.setText(horoscope.getMood());
                mNumberDetail.setText(horoscope.getLuckyNumber());
                mTimeDetail.setText(horoscope.getLuckyTime());
                mColorDetail.setText(horoscope.getColor());

                //stopping shimmer
                showShimmer(false);
            }

            @Override
            public void onFailure(Call<Horoscope> call, Throwable t) {
                //stopping shimmer
                showShimmer(false);
            }
        });
    }

    private void showShimmer(boolean showShimmer) {
        if (showShimmer) {
            mDetailShimmer.startShimmer();
            mDetailShimmer.showShimmer(true);
        } else {
            mDetailShimmer.stopShimmer();
            mDetailShimmer.hideShimmer();
        }
    }
}