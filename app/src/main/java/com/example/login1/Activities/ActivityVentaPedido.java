package com.example.login1.Activities;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.example.login1.Models.Cliente;
import com.example.login1.Models.Producto;
import com.example.login1.Models.Venta;
import com.example.login1.Models.VentaDetalle;
import com.example.login1.R;
import com.example.login1.Splash.SplashActivity;
import com.example.login1.Utils.VolleySingleton;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActivityVentaPedido extends AppCompatActivity implements DialogCliente.DialogListener{
    private RequestQueue mQueue;
    static AutoCompleteTextView autocompleteCliente;
    static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    static Cliente clienteSeleccionado = new Cliente();
    private Button nuevoCliente;
    private  TextView textViewClienteSeleccionado;
    private  TextView textTelefono;
    private  TextView textCorreo;
    private Button btnPedido;
    private static int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta_pedido);
        mQueue = VolleySingleton.getInstance(this).getRequestQueue();
        textViewClienteSeleccionado = findViewById(R.id.labelNombreCliente);
        textTelefono = findViewById(R.id.labelTelefono);
        textCorreo = findViewById(R.id.labelCorreo);
        autocompleteCliente = findViewById(R.id.autocomplete_Cliente);
        btnPedido = findViewById(R.id.btnGuardarVentaPedido);

        final ArrayAdapter<Cliente> adapter = new ArrayAdapter<>(
                ActivityVentaPedido.this, R.layout.custom_list_item,R.id.text_view_list_item, clientes);
        autocompleteCliente.setAdapter(adapter);

        autocompleteCliente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                clienteSeleccionado= (Cliente) adapterView.getAdapter().getItem(i);
                textViewClienteSeleccionado.setText(clienteSeleccionado.getNombre()+" "+
                        clienteSeleccionado.getApellidoP()+" "+clienteSeleccionado.getApellidoM());
                textTelefono.setText(clienteSeleccionado.getTelefono());
                textCorreo.setText(clienteSeleccionado.getEmail());
                autocompleteCliente.setText("");
            }
        });
        autocompleteCliente.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                getClientes();
            }
        });
        btnPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActivityVentaPedido.this,"Espere...",Toast.LENGTH_SHORT).show();
                Venta venta = new Venta();
                venta.setVendedorId(SplashActivity.userData.getId());
                venta.setClienteId(clienteSeleccionado.getId());
                venta.setVentaDetalle(crearVentaDetalle());
                venta.setPagoEfectivo(getIntent().getStringExtra("Efectivo"));
                venta.setTipoVenta("Pedido");
                guardarVenta(venta);
            }
        });
        nuevoCliente = findViewById(R.id.btnNuevoCliente);
        nuevoCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

    }



    private void getClientes() {
        clientes.clear();
        String url = "http://pvmovilbackend.eastus.azurecontainer.io/api/Clientes";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Gson gson = new Gson();
                            JSONArray array =response.getJSONArray("Data");
                            for (int i=0;i<array.length();i++){
                                JSONObject obj = array.getJSONObject(i);
                                clientes.add(gson.fromJson(obj.toString(), Cliente.class));

                            }

                        } catch (Exception e) {
                        }
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ActivityVentaPedido.this,error.toString(),Toast.LENGTH_SHORT).show();
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

    private void crearCliente(final Cliente cliente)
    {

        JSONObject json = new JSONObject();
        try {
            json.put("Nombre",cliente.getNombre());
            json.put("ApellidoP",cliente.getApellidoP());
            json.put("ApellidoM",cliente.getApellidoM());
            json.put("Email",cliente.getEmail());
            json.put("Telefono",cliente.getTelefono());
            json.put("Colonia",cliente.getColonia());
            json.put("Calle",cliente.getCalle());
            json.put("NumeroIntoExt",cliente.getNumeroIntoExt());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = "http://pvmovilbackend.eastus.azurecontainer.io/api/Clientes";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(ActivityVentaPedido.this, "Buena : " + response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ActivityVentaPedido.this, "Error : " + error.getMessage(), Toast.LENGTH_SHORT).show();
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
            Toast.makeText(ActivityVentaPedido.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

        String url = "http://pvmovilbackend.eastus.azurecontainer.io/api/Ventas/";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url,obj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ActivityVendedor.productos.clear();
                        ActivityVendedor.prod.clear();
                        ActivityVendedor.selectedProd.clear();
                        ActivityVendedor.txTotal.setText("0.0");
                        Intent intent = new Intent(ActivityVentaPedido.this,ActivityVendedor.class);
                        startActivity(intent);
                        try {
                            count++;
                            //Toast.makeText(ActivityVentaPedido.this,String.valueOf(count),Toast.LENGTH_LONG).show();
                            Toast.makeText(ActivityVentaPedido.this,"Venta guardada",Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(ActivityVentaPedido.this,"Algo salio mal",Toast.LENGTH_LONG).show();
                        }
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ActivityVentaPedido.this,error.toString(),Toast.LENGTH_LONG).show();
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
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
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


    public void openDialog() {
        DialogCliente dialogCliente = new DialogCliente();
        dialogCliente.show(getSupportFragmentManager(), "Dialog Cliente");
    }

    @Override
    public void datosCliente(Cliente cliente) {
        crearCliente(cliente);
    }
}
