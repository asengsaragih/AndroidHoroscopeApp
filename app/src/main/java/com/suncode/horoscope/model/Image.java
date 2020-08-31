package com.suncode.horoscope.model;

import com.google.gson.annotations.SerializedName;

public class Image {
    private String name;
    private String license;

    @SerializedName("horoscope")
    private Horoscope[] horoscope;

    public String getName() {
        return name;
    }

    public String getLicense() {
        return license;
    }

    public Horoscope[] getHoroscope() {
        return horoscope;
    }

    public class Horoscope {
        private String name;
        private String url;

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }
    }
}
