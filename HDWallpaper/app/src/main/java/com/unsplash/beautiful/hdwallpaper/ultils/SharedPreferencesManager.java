package com.unsplash.beautiful.hdwallpaper.ultils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Trung Tran Thanh on 7/4/2017.
 */

public class SharedPreferencesManager {

    private static final String KEY_RATE = "key_rate";
    private static final String KEY_AGAIN = "key_again";

    public static void setRate(Context context, boolean path) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putBoolean(KEY_RATE, path).apply();
    }

    public static boolean getRate(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(KEY_RATE, false);
    }

    public static void setAgain(Context context, boolean path) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putBoolean(KEY_AGAIN, path).apply();
    }

    public static boolean getAgain(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(KEY_AGAIN, false);
    }
}
