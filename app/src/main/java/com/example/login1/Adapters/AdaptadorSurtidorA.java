package com.example.login1.Adapters;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.login1.Models.Venta;
import com.example.login1.R;

import java.util.ArrayList;
import java.util.Calendar;

public  class AdaptadorSurtidorA extends BaseAdapter {
    private ArrayList<Venta> ventas;
    private Context context;
    private int layout;
    private View v;
    private ArrayList<String> ven;
    private Button btnFecha;
    private View btnView;

    public AdaptadorSurtidorA(Context context, int layout, ArrayList<String> ven) {
        this.context = context;
        this.layout = layout;
        this.ven = ven;

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


        return v;
    }




}