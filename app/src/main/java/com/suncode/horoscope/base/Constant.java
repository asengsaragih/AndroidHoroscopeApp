package com.suncode.horoscope.base;

import android.graphics.drawable.Drawable;

import com.suncode.horoscope.R;

import java.util.ArrayList;

public class Constant {
    public static final String BASE_URL_HOROSCOPE = "https://aztro.sameerkumar.website";
    public static final String BASE_URL_IMAGE = "https://raw.githubusercontent.com/";

    public static final String SIGN_ARIES = "aries";
    public static final String SIGN_TAURUS = "taurus";
    public static final String SIGN_GEMINI = "gemini";
    public static final String SIGN_CANCER = "cancer";
    public static final String SIGN_LEO = "leo";
    public static final String SIGN_VIRGO = "virgo";
    public static final String SIGN_LIBRA = "libra";
    public static final String SIGN_SCORPIO = "scorpio";
    public static final String SIGN_SAGITTARIUS = "sagittarius";
    public static final String SIGN_CAPRICORN = "capricorn";
    public static final String SIGN_AQUARIUS = "aquarius";
    public static final String SIGN_PISCES = "pisces";

    public static final String[] SIGNS_ARRAY = {"aries", "taurus", "gemini", "cancer", "leo", "virgo", "libra", "scorpio", "sagittarius", "capricorn", "aquarius", "pisces"};

    public static final String[] SIGNS_INTERVAL = {
            "21 Mar – 20 Apr",
            "21 Apr – 20 Mei",
            "21 Mei – 20 Jun",
            "21 Jun – 20 Jul",
            "21 Jul – 21 Agu",
            "22 Agu – 22 Sep",
            "23 Sep – 22 Okt",
            "23 Okt – 22 Nov",
            "23 Nov – 20 Des",
            "21 Des – 19 Jan",
            "20 Jan – 18 Feb",
            "19 Febr – 20 Mar"
    };

    public static final int[] SIGNS_ICON = {
            R.drawable.ic_aries,
            R.drawable.ic_taurus,
            R.drawable.ic_gemini,
            R.drawable.ic_cancer,
            R.drawable.ic_leo,
            R.drawable.ic_virgo,
            R.drawable.ic_libra,
            R.drawable.ic_scorpio,
            R.drawable.ic_sagittarius,
            R.drawable.ic_capricorn,
            R.drawable.ic_aquarius,
            R.drawable.ic_pisces
    };

    public static final String DAY_TODAY = "today";
    public static final String DAY_TOMORROW = "tomorrow";
    public static final String DAY_YESTERDAY = "yesterday";
}
