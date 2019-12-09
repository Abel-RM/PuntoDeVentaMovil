package com.example.login1.Activities;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.example.login1.Adapters.AdaptadorDetalleVenta;
import com.example.login1.Models.Producto;
import com.example.login1.Models.Venta;
import com.example.login1.Models.VentaDetalleResultado;
import com.example.login1.R;
import com.example.login1.Utils.Util;
import com.example.login1.Utils.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;


public class ActivityDetalleVenta extends AppCompatActivity{
    private SharedPreferences prefs;
    private RequestQueue mQueue;
    static ListView list;
    static ArrayList<VentaDetalleResultado> detalles=new ArrayList<>();
    static ArrayList<String> prod=new ArrayList<>();
    static AdaptadorDetalleVenta adapterList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalleventa);
        mQueue = VolleySingleton.getInstance(this).getRequestQueue();
        prefs=getSharedPreferences("preferences", Context.MODE_PRIVATE);
        list=  findViewById(R.id.listdetallesVenta);
        getDetalleVenta();

        TextView tx= findViewById(R.id.totalDetalle);
        tx.setText(tx.getText()+getIntent().getExtras().getString("Total"));
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public Context getContext(){
        return ActivityDetalleVenta.this;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_logOut:
                logOut();
                return true;

            case R.id.menu_forget:
                removeSharedPreferences();
                logOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    private void logOut(){
        Intent intent= new Intent(this,login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    private void removeSharedPreferences(){
        prefs.edit().clear().apply();

    }


    private void getDetalleVenta() {
        detalles.clear();
        String id =getIntent().getExtras().getString("Id");
        String url = "http://pvmovilbackend.eastus.azurecontainer.io/api/Ventas/GetDetalleVenta?id="+id;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Gson gson = new Gson();
                            JSONArray array =response.getJSONArray("Data");
                            for (int i=0;i<array.length();i++){
                                JSONObject obj = array.getJSONObject(i);
                                detalles.add(gson.fromJson(obj.toString(), VentaDetalleResultado.class));
                                prod.add(obj.getString("Nombre"));
                            }
                            adapterList = new AdaptadorDetalleVenta(ActivityDetalleVenta.this,R.layout.custom_list_item,detalles);
                            list.setAdapter(adapterList);
                        } catch (Exception e) {
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ActivityDetalleVenta.this,error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + login.UserToken);
                return headers;
            }
        };
        mQueue.add(jsonObjectRequest);
    }


}
