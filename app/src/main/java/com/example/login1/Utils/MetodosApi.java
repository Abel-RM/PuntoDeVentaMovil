package com.example.login1.Utils;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;



public class MetodosApi {

    public static String getToken(final String email, final String password, Context context){
        final String[] token = new String[1];
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://localhost:5000/api/Usuarios/Login/";
        StringRequest jsonArrayRequest = new StringRequest
                (Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        token[0] =response.toString();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                    }
                })
        {
            @Override
            protected HashMap<String, String> getParams()
            {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("Email", email);
                params.put("Password", password);
                return params;
            }
        };
        queue.add(jsonArrayRequest);

        if(!TextUtils.isEmpty(token[0])){
            return token[0];
        }else
            return null;
    }

}
