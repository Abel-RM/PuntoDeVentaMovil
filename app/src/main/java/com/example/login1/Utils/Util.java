package com.example.login1.Utils;

import android.content.SharedPreferences;

public class Util {
    public static String getUserMailPrefs(SharedPreferences prefs){
        return prefs.getString("email","");
    }
    public static  String getUserPassPrefs(SharedPreferences prefs){
        return prefs.getString("pass","");
    }
}
