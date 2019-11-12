package com.example.login1.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.login1.R;

import java.util.List;

public  class MyAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<String> names;


    public MyAdapter(Context context, int layout, List<String> names) {
        this.context = context;
        this.layout = layout;
        this.names = names;

    }

    @Override
    public int getCount() {
        return this.names.size();
    }

    @Override
    public Object getItem(int i) {
        return this.names.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        View v = convertView;

        LayoutInflater layoutInflater=LayoutInflater.from(this.context);
        v=layoutInflater.inflate(R.layout.list_item,null);

        String currentName = names.get(position);

        TextView textView =(TextView) v.findViewById(R.id.textView);
        textView.setText(currentName);

        ImageView img = (ImageView) v.findViewById(R.id.imageView);
        int imgPosition= ActivityVendedor.prod.indexOf(currentName);
        Glide.with(context.getApplicationContext()).load(ActivityVendedor.productos.get(imgPosition).getImagenRuta()).into(img);

        return v;
    }
}
