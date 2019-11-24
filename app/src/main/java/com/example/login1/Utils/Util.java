package com.example.login1.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login1.Models.Producto;

import java.util.ArrayList;

public class Util extends AppCompatActivity {
    public static ArrayList<Producto> auxiliar= new ArrayList<>();

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
