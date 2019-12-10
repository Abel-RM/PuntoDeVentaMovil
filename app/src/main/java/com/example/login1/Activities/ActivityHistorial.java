package com.example.login1.Activities;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.example.login1.Adapters.AdaptadorHistorial;
import com.example.login1.Models.VentaResultado;
import com.example.login1.R;
import com.example.login1.Splash.SplashActivity;
import com.example.login1.Utils.VolleySingleton;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class ActivityHistorial extends AppCompatActivity{
    private SharedPreferences prefs;
    private RequestQueue mQueue;
    public static String Id;
    //static ListView list;
    //static AutoCompleteTextView editText;
    //static ArrayList<Producto> productos=new ArrayList<>();
    //static ArrayList<String> venta=new ArrayList<>();
    //static ArrayList<VentaResultado> ventaSeleccionada = new ArrayList<VentaResultado>();
    //static TextView txTotal;
    static AdaptadorHistorial adapterList;
    ArrayList<VentaResultado> ventas= new ArrayList<VentaResultado>();
    static ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        mQueue = VolleySingleton.getInstance(this).getRequestQueue();
        prefs=getSharedPreferences("preferences", Context.MODE_PRIVATE);
        list=  findViewById(R.id.list_historial);
        getVentas();
    }

    @Override
    public void onResume() {
        super.onResume();
        //actualizarLista(Util.auxiliar);
        //Util.auxiliar.clear();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public Context getContext(){
        return ActivityHistorial.this;
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


    private void getVentas() {
        ventas.clear();
        //String fechaInicioDeLosTiempos = "01/01/0001";
        String fechaInicioDeLosTiempos = "01-01-2019";
        String fechaFin= "01-01-2030";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("MM-dd-yyyy");
        String fechaActual = format1.format(cal.getTime());
        //String url = "http://pvmovilbackend.eastus.azurecontainer.io/api/Ventas?fecha1="+fechaInicioDeLosTiempos+"&&fecha2="+fechaFin;
        String url = "http://pvmovilbackend.eastus.azurecontainer.io/api/Ventas?fecha1=01/01/2019&fecha2=12/31/2019";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Gson gson = new Gson();
                            JSONArray array =response.getJSONArray("Data");
                            for (int i=0;i<array.length();i++){
                                JSONObject obj = array.getJSONObject(i);
                                VentaResultado ventaResultado =gson.fromJson(obj.toString(), VentaResultado.class);
                                if(ventaResultado.getIdVendedor().equals(SplashActivity.userData.getId())) {
                                    ventas.add(ventaResultado);
                                }
                            }

                            adapterList = new AdaptadorHistorial(ActivityHistorial.this,R.layout.custom_list_item,ventas);
                            list.setAdapter(adapterList);

                        } catch (Exception e) {
                        }
                        //Toast.makeText(getContext(),response.toString(),Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ActivityHistorial.this,error.toString(),Toast.LENGTH_SHORT).show();
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