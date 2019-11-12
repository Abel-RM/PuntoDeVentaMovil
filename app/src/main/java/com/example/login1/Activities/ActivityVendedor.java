package com.example.login1.Activities;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.login1.Models.Producto;
import com.example.login1.Models.Usuario;
import com.example.login1.R;
import com.example.login1.Utils.VolleySingleton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class ActivityVendedor extends AppCompatActivity {
    private SharedPreferences prefs;
    private RequestQueue mQueue;
    private ListView list;
    static AutoCompleteTextView editText;
    static ArrayList<Producto> productos=new ArrayList<>();
    static ArrayList<String> prod=new ArrayList<>();
    static ArrayList<String> selectedProd=new ArrayList<>();
    private TextView txTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendedor);
        mQueue = VolleySingleton.getInstance(this).getRequestQueue();
        prefs=getSharedPreferences("preferences", Context.MODE_PRIVATE);
        txTotal=findViewById(R.id.precio);
        list=(ListView)findViewById(R.id.listView);

        editText = findViewById(R.id.actv);

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                productos.clear();
                prod.clear();
                getProducts();
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(ActivityVendedor.this,
                        R.layout.custom_list_item, R.id.text_view_list_item,prod);
                editText.setAdapter(adapter);
            }
        });
        editText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedProd.add(adapterView.getItemAtPosition(i).toString());
                MyAdapter adapter1 = new MyAdapter(ActivityVendedor.this,R.layout.custom_list_item,selectedProd);
                list.setAdapter(adapter1);
                editText.setText("");
                txTotal.setText("Total: $ "+calcularTotal(productos,selectedProd));


            }
        });

        ImageView camara= findViewById(R.id.camara);
        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ActivityVendedor.this,"Camara",Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
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

    private void searchProducts(String product) {
        String url = "http://pvmovil.westus.azurecontainer.io/api/Productos/BuscarProductos?valor="+product;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i=0;i<response.length();i++){
                            try {
                                prod.add(response.getJSONObject(i).getString("Nombre"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> cabeceras = new HashMap<String, String>();
                cabeceras.put("Token", login.UserToken);
                return cabeceras;
            }
        };
        mQueue.add(jsonArrayRequest);

    }



    private void getProducts() {
        String url = "http://pvmovil.westus.azurecontainer.io/api/Productos";


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url,null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i=0;i<response.length();i++){
                            try {
                                Gson gson = new Gson();
                                productos.add(gson.fromJson(response.getJSONObject(i).toString(), Producto.class));
                                prod.add(response.getJSONObject(i).getString("Nombre"));
                                //images.add(response.getJSONObject(i).getString("ImagenRuta"));

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> cabeceras = new HashMap<String, String>();
                cabeceras.put("Token", login.UserToken);
                return cabeceras;
            }
        };
        mQueue.add(jsonArrayRequest);

    }

    private Double calcularTotal(ArrayList<Producto> lista,ArrayList<String> select){
        Double total=0.0;
        int index=0;
        for (String item:select){

            for (int i=0;i<lista.size();i++){
                if (lista.get(i).getNombre().equals(item)){
                    index=i;
                    break;
                }
            }
            total=total+lista.get(index).getPrecioMenudeo();
        }
        return total;
    }


}

