package com.example.login1.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.login1.Activities.MainActivity;
import com.example.login1.Activities.login;
import com.example.login1.Utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

public class SplashActivity extends AppCompatActivity {
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        String email = Util.getUserMailPrefs(prefs);
        String password = Util.getUserPassPrefs(prefs);
        String url = "http://pvmovil.westus.azurecontainer.io/api/Usuarios/Login/";
        final JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("Email",email);
            jsonBody.put("Password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            login.UserToken=response.getString("Token");

                        } catch (JSONException e) {

                        }
                        Intent intentMain = new Intent(com.example.login1.Splash.SplashActivity.this, MainActivity.class);
                        startActivity(intentMain);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Intent intentLogin = new Intent(com.example.login1.Splash.SplashActivity.this, login.class);
                        startActivity(intentLogin);

                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


        finish();

    }

}
