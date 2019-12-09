package com.example.login1.Adapters;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.example.login1.Activities.ActivityDetalleVenta;
import com.example.login1.Activities.ActivityVentaPedido;
import com.example.login1.Activities.login;
import com.example.login1.Models.Venta;
import com.example.login1.Models.VentaDetalleResultado;
import com.example.login1.Models.VentaResultado;
import com.example.login1.R;
import com.example.login1.Splash.SplashActivity;
import com.example.login1.Utils.VolleySingleton;
import com.example.login1.ui.main.FragmentTodasVentas;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public  class AdaptadorSurtidorT extends BaseAdapter  implements DatePickerDialog.OnDateSetListener {
    private Context context;
    private int layout;
    private View v;
    private ArrayList<VentaResultado> ven;
    private Button btnFecha;
    private Button btnGuardar;
    private View btnView;
    private RequestQueue mQueue;
    private int pVen;

    public AdaptadorSurtidorT(Context context, int layout, ArrayList<VentaResultado> vent) {
        this.context = context;
        this.layout = layout;
        this.ven = vent;

    }
    @Override
    public int getCount() {
        return this.ven.size();
    }

    @Override
    public Object getItem(int i) {
        return this.ven.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup viewGroup) {
        v = convertView;
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        v = layoutInflater.inflate(R.layout.item_surtidor_todas, null);

        mQueue = VolleySingleton.getInstance(v.getContext()).getRequestQueue();
        TextView t1 = v.findViewById(R.id.tx_nombre_cliente_todas);
        t1.setText(ven.get(position).getCliente());

        TextView t2 = v.findViewById(R.id.tx_nombre_vendedor_todas);
        t2.setText(ven.get(position).getVendedor());

        TextView t3 = v.findViewById(R.id.tx_fecha_venta_todas);
        t3.setText(ven.get(position).getFechaVenta());

        TextView t4 = v.findViewById(R.id.tx_direccion_todas);
        t4.setText(ven.get(position).getDomicilio());


        btnFecha= v.findViewById(R.id.btn_asignar_fecha_todas);

        btnGuardar= v.findViewById(R.id.btn_guardar_todas);
        btnGuardar.setId(position);

        Button btnDetalle = v.findViewById(R.id.btn_detalle_todas);
        btnDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(v.getContext(), ActivityDetalleVenta.class);
                intent.putExtra("Id",ven.get(position).getId());
                intent.putExtra("Total",ven.get(position).getTotal());
                v.getContext().startActivity(intent);
            }
        });


        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oyente(view,position);
            }
        });
        Button b=v.findViewById(position);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oyente(view,position);
            }
        });




        return v;
    }
    private void oyente(View view,int p){
        Button boton = view.findViewById(view.getId());
        if (boton.getText().equals("Guardar")){
            if (ven.get(p).getFechaProgramadaEntrega().equals("0001-01-01T00:00:00Z")){
                Toast.makeText(v.getContext(),"Fecha incorrecta",Toast.LENGTH_LONG).show();
            }else
                setFecha(boton.getId());

        }else{
            showDatePickerDialog();
            btnView=view;
            pVen=p;
        }

    }


    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                v.getContext(),
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        int m = month+1;
        String date = year+"/"+m+"/"+day;
        Button b =btnView.findViewById(btnView.getId());
        b.setText(date);
        ven.get(pVen).setFechaProgramadaEntrega(date);
    }

    private void setFecha(final int position) {
        String url = "http://pvmovilbackend.eastus.azurecontainer.io/api/Ventas/ProgramarFechaEntrega?" +
                "id="+ ven.get(position).getId()+
                "&FechaProgramada="+ ven.get(position).getFechaProgramadaEntrega()+
                "&SurtidorId="+SplashActivity.userData.getId();


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.PUT, url,null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(v.getContext(),"Fecha guardada",Toast.LENGTH_LONG).show();
                        ven.remove(position);
                        FragmentTodasVentas.actualizar();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(v.getContext(),error.toString(),Toast.LENGTH_LONG).show();
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
