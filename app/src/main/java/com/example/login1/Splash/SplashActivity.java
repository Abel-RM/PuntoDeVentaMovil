package com.example.login1.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.login1.Activities.MainActivity;
import com.example.login1.Activities.login;
import com.example.login1.Utils.Util;

public class SplashActivity extends AppCompatActivity {
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        Intent intentLogin = new Intent(this, login.class);
        Intent intentMain = new Intent(this, MainActivity.class);
        if (!TextUtils.isEmpty(Util.getUserMailPrefs(prefs))&&!TextUtils.isEmpty(Util.getUserPassPrefs(prefs))){
            startActivity(intentMain);
        }else{
            startActivity(intentLogin);
        }
        finish();

    }

}
