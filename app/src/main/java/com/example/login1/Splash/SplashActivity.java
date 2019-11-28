package com.example.login1.Splash;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.login1.Activities.ActivitySurtidor;
import com.example.login1.Activities.ActivityVendedor;
import com.example.login1.Activities.login;
import com.example.login1.Models.Usuario;
import com.example.login1.Utils.Util;
import com.example.login1.Utils.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SplashActivity extends AppCompatActivity {
    private SharedPreferences prefs;
    private RequestQueue mQueue;
    public static Usuario userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        String email = Util.getUserMailPrefs(prefs);
        String password = Util.getUserPassPrefs(prefs);
        String url = "http://pvmovilbackend.eastus.azurecontainer.io/api/Usuarios/Login";
        mQueue = VolleySingleton.getInstance(this).getRequestQueue();
        validar(email,password,url);



    }
    private void validar(final String email, String password, String url){

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
                        Gson gson = new Gson();
                        try {
                            login.UserToken=  response.getJSONObject("Data").getString("Token");
                            userData = gson.fromJson(response.getJSONObject("Data").getJSONObject("Usuario").toString(), Usuario.class);

                        } catch (JSONException e) {

                        }
                        if (userData.getRol().equals("Vendedor")){
                            pantallaVendedor();
                        }else
                            if (userData.getRol().equals("Surtidor")){
                                pantallaSurtidor();
                            }

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
        mQueue.add(stringRequest);

        finish();
    }
    private void pantallaVendedor(){
        Intent intent = new Intent(com.example.login1.Splash.SplashActivity.this, ActivityVendedor.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    private void pantallaSurtidor(){
        Intent intent = new Intent(com.example.login1.Splash.SplashActivity.this, ActivitySurtidor.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}



