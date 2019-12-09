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

import com.example.login1.Activities.ActivityDetalleVenta;
import com.example.login1.Activities.Firma;
import com.example.login1.Models.Venta;
import com.example.login1.Models.VentaResultado;
import com.example.login1.R;

import java.util.ArrayList;
import java.util.Calendar;

public  class AdaptadorSurtidorA extends BaseAdapter {
    private Context context;
    private int layout;
    private View v;
    private ArrayList<VentaResultado> ven;

    public AdaptadorSurtidorA(Context context, int layout, ArrayList<VentaResultado> venta) {
        this.context = context;
        this.layout = layout;
        this.ven = venta;

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
        v = layoutInflater.inflate(R.layout.item_surtidor_asignadas, null);

        TextView t1 = v.findViewById(R.id.tx_nombre_cliente_asignadas);
        t1.setText(ven.get(position).getCliente());

        TextView t2 = v.findViewById(R.id.tx_nombre_vendedor_asignadas);
        t2.setText(ven.get(position).getVendedor());

        TextView t3 = v.findViewById(R.id.tx_fecha_venta_asignadas);
        t3.setText(ven.get(position).getFechaProgramadaEntrega());

        TextView t4 = v.findViewById(R.id.tx_direccion_asignadas);
        t4.setText(ven.get(position).getDomicilio());


        Button btnDetalle = v.findViewById(R.id.btn_detalle_asignadas);
        btnDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(v.getContext(), ActivityDetalleVenta.class);
                intent.putExtra("Id",ven.get(position).getId());
                intent.putExtra("Total",ven.get(position).getTotal());
                v.getContext().startActivity(intent);
            }
        });

        Button btnFirma = v.findViewById(R.id.btn_firma);
        btnFirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Firma.class);
                intent.putExtra("VentaId",ven.get(position).getId());
                Toast.makeText(v.getContext(),ven.get(position).getEstadoVenta(),Toast.LENGTH_SHORT).show();
                v.getContext().startActivity(intent);
            }
        });


        return v;
    }







}