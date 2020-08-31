package com.suncode.horoscope.base;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    public Session(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        editor = preferences.edit();

        //Todo: jangan lupa dihapus kodingan dibawah ini
        editor.putString("name", "Aldi Saragih");
        editor.putString("horoscope", Constant.SIGN_SAGITTARIUS);
        editor.putBoolean("check", true);
        editor.commit();
    }

    public void setData(String name, String horoscope, boolean isTrue) {
        editor.putString("name", name);
        editor.putString("horoscope", horoscope);
        editor.putBoolean("check", isTrue);
        editor.commit();
    }

    public boolean isTrue() {
        return preferences.getBoolean("check", false);
    }

    public String getUserHoroscope() {
        return preferences.getString("horoscope", null);
    }

    public String getUserName() {
        return preferences.getString("name", null);
    }

}
