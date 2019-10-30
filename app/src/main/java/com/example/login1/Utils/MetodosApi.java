package com.example.login1.Utils;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.login1.Activities.login;

import org.json.JSONException;
import org.json.JSONObject;

public class MetodosApi extends AppCompatActivity {

    public static void getToken(final String email, final String password, final Context context){
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
                            Toast.makeText(context,login.UserToken,Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }




}
