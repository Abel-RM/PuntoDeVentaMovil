package com.example.login1.Adapters;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login1.Models.Venta;
import com.example.login1.Models.VentaResultado;
import com.example.login1.R;

import java.util.ArrayList;
import java.util.Calendar;

public  class AdaptadorSurtidorT extends BaseAdapter  implements DatePickerDialog.OnDateSetListener {
    private Context context;
    private int layout;
    private View v;
    private ArrayList<VentaResultado> ven;
    private Button btnFecha;
    private View btnView;

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


        TextView t1 = v.findViewById(R.id.tx_nombre_cliente_todas);
        t1.setText(ven.get(position).getCliente());

        TextView t2 = v.findViewById(R.id.tx_nombre_vendedor_todas);
        t2.setText(ven.get(position).getVendedor());

        TextView t3 = v.findViewById(R.id.tx_fecha_venta_todas);
        t3.setText(ven.get(position).getFechaVenta());

        TextView t4 = v.findViewById(R.id.tx_direccion_todas);
        t4.setText(ven.get(position).getDomicilio());


        btnFecha= v.findViewById(R.id.btn_asignar_fecha_todas);
        btnFecha.setId(position);

        Button b=v.findViewById(position);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
                btnView=view;
            }
        });


        return v;
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
        String date = day+"/"+m+"/"+year;
        Button b =btnView.findViewById(btnView.getId());
        b.setText(date);
    }
}
