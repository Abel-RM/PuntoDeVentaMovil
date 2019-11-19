package com.example.login1.Activities;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.login1.Models.Producto;
import com.example.login1.R;
import com.example.login1.Utils.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ActivityVendedor extends AppCompatActivity{
    private SharedPreferences prefs;
    private RequestQueue mQueue;
    //com.baoyz.swipemenulistview.SwipeMenuListView
    static ListView list;
    static AutoCompleteTextView editText;
    static ArrayList<Producto> productos=new ArrayList<>();
    static ArrayList<String> prod=new ArrayList<>();
    static ArrayList<Producto> selectedProd=new ArrayList<>();
    static TextView txTotal;
    static MyAdapter adapterList;
    static ArrayList<Producto> code= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendedor);
        mQueue = VolleySingleton.getInstance(this).getRequestQueue();
        prefs=getSharedPreferences("preferences", Context.MODE_PRIVATE);
        txTotal=findViewById(R.id.precio);
        list=  findViewById(R.id.listView);
        String g =getIntent().getStringExtra("codigo");
        //Producto p = ActivityVendedor.searchProductByCode(code);
        //ActivityVendedor.selectedProd.add(p);
        editText = findViewById(R.id.actv);
        adapterList = new MyAdapter(ActivityVendedor.this,R.layout.custom_list_item,selectedProd);

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
                Producto prod = new Producto();
                String nombre =adapterView.getItemAtPosition(i).toString();
                prod.setNombre(nombre);
                //buscar(nombre)
                if (true){
                    prod.setPedidos(1);
                    selectedProd.add(prod);
                    MyAdapter adapter1 = new MyAdapter(ActivityVendedor.this,R.layout.custom_list_item,selectedProd);
                    list.setAdapter(adapter1);
                    editText.setText("");
                    txTotal.setText("Total: $ "+calcularTotal(productos,selectedProd));
                }else editText.setText("");


            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(ActivityVendedor.this)
                        .setTitle("Borrar Producto")
                        .setMessage("Estas seguro?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                selectedProd.remove(position);
                                MyAdapter adapter1 = new MyAdapter(ActivityVendedor.this,R.layout.custom_list_item,selectedProd);
                                list.setAdapter(adapter1);
                                txTotal.setText("Total: $ "+calcularTotal(productos,selectedProd));
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                return true;
            }
        });


        ImageView camara= findViewById(R.id.camara);
        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityVendedor.this, SimpleScannerActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onResume() {
        getProducts();
        try {
            for(Producto item : code){
                //Toast.makeText(this,item.getCodigoBarra(),Toast.LENGTH_SHORT).show();
                //Toast.makeText(this,productos.get(0).getCodigoBarra(),Toast.LENGTH_SHORT).show();
                //int x = productos.indexOf(item.getCodigoBarra());
                Producto p= searchProductByCode(item.getCodigoBarra());
                //Toast.makeText(this, p.getNombre(),Toast.LENGTH_SHORT).show();
                item.setNombre(p.getNombre());
            }
            selectedProd.addAll(code);
            MyAdapter adapter1 = new MyAdapter(ActivityVendedor.this,R.layout.custom_list_item,selectedProd);
            list.setAdapter(adapter1);

        }catch (Exception e){
            Toast.makeText(this, e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public Context getContext(){
        return ActivityVendedor.this;
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



    private void getProducts() {
        String url = "http://pvmovil.westus.azurecontainer.io/api/Productos";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url,null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Gson gson = new Gson();
                            JSONArray array =response.getJSONArray("Data");
                            for (int i=0;i<array.length();i++){
                                JSONObject obj = array.getJSONObject(i);
                                productos.add(gson.fromJson(obj.toString(), Producto.class));
                                prod.add(obj.getString("Nombre"));
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
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
        mQueue.add(jsonObjectRequest);

    }

    public static Double calcularTotal(ArrayList<Producto> lista,ArrayList<Producto> select){
        Double total=0.0;
        int index=0;
        for (Producto item:select){

            for (int i=0;i<lista.size();i++){
                if (lista.get(i).getNombre().equals(item.getNombre())){
                    index=i;
                    break;
                }
            }
            int cant=item.getPedidos();
            if (cant>5){
                total=total+lista.get(index).getPrecioMayoreo()*cant;
            }else
                total=total+lista.get(index).getPrecioMenudeo()*cant;

        }
        return total;
    }
    public boolean buscar(String name){
        for (Producto item: selectedProd){
            if (item.getNombre().equals(name)){
                return false;
            }
        }
        return true;
    }
    private Producto searchProductByCode(String c){
        for (int i=0;i<productos.size();i++){
            if (c.equals(productos.get(i).getCodigoBarra())){
                Toast.makeText(this, "Si encontrado",Toast.LENGTH_SHORT).show();
                return productos.get(i);
            }

        }
        Toast.makeText(this, "No encontrado",Toast.LENGTH_SHORT).show();
        return null;
    }


}
