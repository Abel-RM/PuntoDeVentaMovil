package com.example.login1.Adapters;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.login1.Activities.ActivityDetalleVenta;
import com.example.login1.Activities.ActivityHistorial;
import com.example.login1.Models.VentaResultado;
import com.example.login1.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AdaptadorHistorial extends BaseAdapter {
    private ArrayList<VentaResultado> ventas;
    private Context context;
    private int layout;
    private View v;
    SimpleDateFormat format1 = new SimpleDateFormat("MM-dd-yyyy");



    public AdaptadorHistorial(Context context, int layout, ArrayList<VentaResultado> ventas) {
        this.context = context;
        this.layout = layout;
        this.ventas = ventas;
    }
    @Override
    public int getCount() {
        return this.ventas.size();
    }

    @Override
    public Object getItem(int i) {
        return this.ventas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        v = convertView;
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        v = layoutInflater.inflate(R.layout.item_historial, null);

        TextView fechaVenta = v.findViewById(R.id.fecha_venta_historial);
        fechaVenta.setText(ventas.get(position).getFechaVenta().substring(0,10));
        TextView fechaEntrega = v.findViewById(R.id.fecha_entrega_historial);
        fechaEntrega.setText(ventas.get(position).getFechaEntrega().substring(0,10));

        TextView tipoVenta = v.findViewById(R.id.tipo_venta_historial);
        tipoVenta.setText(ventas.get(position).getTipoVenta());

        TextView cliente = v.findViewById(R.id.nombre_cliente_historial);
        cliente.setText(ventas.get(position).getCliente());

        TextView total= v.findViewById(R.id.total_historial);
        total.setText(ventas.get(position).getTotal());

        Button detalle = v.findViewById(R.id.btn_detalle_historial);
        detalle.setOnClickListener(
                new View.OnClickListener()
                {   @Override
                    public void onClick(View view)
                    {
                        Intent intent = new Intent(v.getContext(),ActivityDetalleVenta.class);
                        intent.putExtra("Id",ventas.get(position).getId());
                        intent.putExtra("Total",String.valueOf(ventas.get(position).getTotal()));
                       context.startActivity(intent);
                    }
                }
        );

        return v;
    }
}



