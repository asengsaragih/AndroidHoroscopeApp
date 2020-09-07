package com.suncode.horoscope.model;

import android.graphics.drawable.Drawable;

public class HoroscopeCollection {
    private String title;
    private String interval;
    private Drawable icon;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public String getInterval() {
        return interval;
    }

    public Drawable getIcon() {
        return icon;
    }
}
