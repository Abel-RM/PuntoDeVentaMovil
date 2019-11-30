package com.example.login1.Activities;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.ContextMenu;
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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.login1.Models.Cliente;
import com.example.login1.Models.Producto;
import com.example.login1.Models.Usuario;
import com.example.login1.Models.Venta;
import com.example.login1.Models.VentaDetalle;
import com.example.login1.R;
import com.example.login1.Splash.SplashActivity;
import com.example.login1.Utils.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
public class ActivityPago extends AppCompatActivity  {
    private SharedPreferences prefs;
    private RequestQueue mQueue;
    private TextView cambio;
    private EditText efectivo;
    private ToggleButton tipoVenta;
    private Button btnComprar;
    private String preciototal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);

        prefs=getSharedPreferences("preferences", Context.MODE_PRIVATE);
        mQueue = VolleySingleton.getInstance(this).getRequestQueue();
        cambio = findViewById(R.id.labelCambio);
        efectivo = findViewById(R.id.editTextEfectivo);
        tipoVenta = findViewById(R.id.toggleButton);
        btnComprar = findViewById(R.id.btnGuardar);

        preciototal = getIntent().getStringExtra("Total");
        final TextView total = findViewById(R.id.labelTotal);
        total.setText(preciototal);

        efectivo.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                DecimalFormat form = new DecimalFormat("####.##");

                try {
                    double t;
                    t = Double.parseDouble(String.valueOf(total.getText()));
                    double e;
                    e = Double.parseDouble(String.valueOf(efectivo.getText()));
                    if (e>=t){
                        cambio.setText(String.valueOf(Double.parseDouble(form.format(e-t))));
                    }
                }catch (Exception e){}

                return false;
            }
        });


        tipoVenta.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    btnComprar.setText("Siguiente");
                }else {
                    btnComprar.setText("Finalizar");
                }
            }
        });
        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tipoVenta.isChecked()){
                    Intent intent = new Intent(ActivityPago.this,ActivityVentaPedido.class);
                    intent.putExtra("PrecioTotal", preciototal);
                    intent.putExtra("Cambio",cambio.getText().toString());
                    intent.putExtra("Efectivo",efectivo.getText().toString());
                    startActivity(intent);
                }else{
                    Toast.makeText(ActivityPago.this,"Espere...",Toast.LENGTH_SHORT).show();
                    Venta venta = new Venta();
                    venta.setVendedorId(SplashActivity.userData.getId());
                    venta.setVentaDetalle(crearVentaDetalle());
                    venta.setPagoEfectivo(efectivo.getText().toString());
                    venta.setTipoVenta("Venta sucursal");
                    guardarVenta(venta);
                }

            }
        });


    }
    private void guardarVenta(Venta venta){
        String det = new Gson().toJson(venta.getVentaDetalle() );
        det=det.trim();
        JSONObject obj = new JSONObject();
        try {
            JSONArray jd = new JSONArray(det);
            obj.put("VendedorId",venta.getVendedorId());
            obj.put("ClienteId",venta.getClienteId());
            obj.put("VentaDetalle",jd);
            obj.put("PagoEfectivo",venta.getPagoEfectivo());
            obj.put("TipoVenta",venta.getTipoVenta());

        } catch (JSONException e) {
            Toast.makeText(ActivityPago.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

        String url = "http://pvmovilbackend.eastus.azurecontainer.io/api/Ventas/";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url,obj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Toast.makeText(ActivityPago.this,"Venta guardada",Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(ActivityPago.this,"Algo salio mal",Toast.LENGTH_LONG).show();
                        }
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ActivityPago.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + login.UserToken);
                return headers;
            }
        };
        mQueue.add(jsonObjectRequest);
    }
    private ArrayList<VentaDetalle> crearVentaDetalle(){
        ArrayList<VentaDetalle> ventaDetalles = new ArrayList<>();

        VentaDetalle detalle;
        for (Producto item : ActivityVendedor.selectedProd){
            detalle= new VentaDetalle();
            detalle.setProductoId(item.getId());
            detalle.setCantidad(String.valueOf(item.getPedidos()));
            ventaDetalles.add(detalle);
        }
        return ventaDetalles;
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





}

