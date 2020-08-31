package com.suncode.horoscope;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.suncode.horoscope.base.Session;
import com.suncode.horoscope.helper.ApiClient;
import com.suncode.horoscope.model.Horoscope;
import com.suncode.horoscope.model.Image;
import com.suncode.horoscope.service.ApiService;

import java.lang.reflect.Array;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.suncode.horoscope.base.Constant.DAY_TODAY;
import static com.suncode.horoscope.base.Constant.DAY_TOMORROW;
import static com.suncode.horoscope.base.Constant.SIGN_SAGITTARIUS;

public class MainActivity extends AppCompatActivity {
    private ApiService mHoroscopeService;
    private ApiService mImageService;
    private Session mSession;

    private ImageView mToolbarImageview;
    private ProgressBar mProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHoroscopeService = ApiClient.horoscopeBuild().create(ApiService.class);
        mImageService = ApiClient.imageBuild().create(ApiService.class);

        mProgressbar = findViewById(R.id.progress_main);
        mToolbarImageview = findViewById(R.id.toolbar_thumbnails_main);

        mSession = new Session(this);

        if (!mSession.isTrue()) {
            //Todo: buat intro activity
        }

        getData();
        setToolbar();
    }

    private void setToolbar() {
        Call<Image> call = mImageService.getImage();
        call.enqueue(new Callback<Image>() {
            @Override
            public void onResponse(Call<Image> call, Response<Image> response) {

                Image data = response.body();
                Image.Horoscope[] horoscopes = data.getHoroscope();

                for (int i = 0; i < horoscopes.length; i++) {
                    if (horoscopes[i].getUrl().contains(mSession.getUserHoroscope())) {

                        Glide.with(getApplicationContext())
                                .load(horoscopes[i].getUrl())
                                .listener(new RequestListener<Drawable>() {
                                    @Override
                                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                        mProgressbar.setVisibility(View.GONE);
                                        return false;
                                    }

                                    @Override
                                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                        mProgressbar.setVisibility(View.GONE);
                                        return false;
                                    }
                                })
                                .diskCacheStrategy(DiskCacheStrategy.DATA)
                                .into(mToolbarImageview);
                    }
                }
            }

            @Override
            public void onFailure(Call<Image> call, Throwable t) {

            }
        });
    }

    private void getData() {
        Call<Horoscope> call = mHoroscopeService.getData(mSession.getUserHoroscope(), DAY_TODAY);

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