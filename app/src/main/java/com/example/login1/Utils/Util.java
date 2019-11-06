package com.example.login1.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class Util extends AppCompatActivity {

    public static String getUserMailPrefs(SharedPreferences prefs){
        return prefs.getString("email","");
    }
    public static  String getUserPassPrefs(SharedPreferences prefs){
        return prefs.getString("pass","");
    }
    public static  boolean getUserRemember(SharedPreferences prefs){
        return prefs.getBoolean("remember",false);
    }
    public static  String getUserToken(SharedPreferences prefs){
        return prefs.getString("token","");
    }


}
