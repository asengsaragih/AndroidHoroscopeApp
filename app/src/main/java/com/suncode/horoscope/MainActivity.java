package com.suncode.horoscope;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.suncode.horoscope.adapter.HoroscopeListAdapter;
import com.suncode.horoscope.base.Constant;
import com.suncode.horoscope.model.HoroscopeCollection;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "CHECKTAG";

    private RecyclerView mHoroscopeRecycleview;
    private HoroscopeListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hide action bar
        getSupportActionBar().hide();
        //transparent status/notification bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //change color text in status/notification bar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        mHoroscopeRecycleview = findViewById(R.id.recycleview_horoscope);

        showHoroscopeList();
    }

    void showHoroscopeList() {
        //configure recycleview data with grid
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 3, RecyclerView.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                //disable scroll in recycle view
                return false;
            }
        };

        mHoroscopeRecycleview.setLayoutManager(layoutManager);

        mAdapter = new HoroscopeListAdapter(MainActivity.this, horoscopeData());
        mHoroscopeRecycleview.setAdapter(mAdapter);
    }

    private List<HoroscopeCollection> horoscopeData() {
        //set data before run application
        List<HoroscopeCollection> data = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            HoroscopeCollection collection = new HoroscopeCollection();

            collection.setIcon(getDrawable(Constant.SIGNS_ICON[i]));
            collection.setInterval(Constant.SIGNS_INTERVAL[i]);
            collection.setTitle(Constant.SIGNS_ARRAY[i]);

            data.add(collection);
        }

        return data;
    }
}