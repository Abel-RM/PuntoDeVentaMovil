package com.example.login1.Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login1.Activities.ActivityHistorial;
import com.example.login1.R;
import java.util.ArrayList;


public  class AdaptadorHistorial extends BaseAdapter {
    private ArrayList<String> ejem;
    private Context context;
    private int layout;
    private View v;

    public AdaptadorHistorial(Context context, int layout, ArrayList<String> ejem) {
        this.context = context;
        this.layout = layout;
        this.ejem = ejem;

    }
    @Override
    public int getCount() {
        return this.ejem.size();
    }

    @Override
    public Object getItem(int i) {
        return this.ejem.get(i);
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

        TextView tx = v.findViewById(R.id.tx_nombre);
        tx.setText(ejem.get(position));


        return v;
    }
}



