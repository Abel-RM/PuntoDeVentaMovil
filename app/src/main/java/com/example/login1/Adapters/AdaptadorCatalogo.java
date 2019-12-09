package com.example.login1.Adapters;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.example.login1.Models.Categoria;
import com.example.login1.Models.VentaResultado;
import com.example.login1.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AdaptadorCatalogo extends BaseAdapter {
    private ArrayList<Categoria> categorias;
    private Context context;
    private int layout;
    private View v;
    public AdaptadorCatalogo(Context context, int layout, ArrayList<Categoria> categorias) {
        this.context = context;
        this.layout = layout;
        this.categorias = categorias;
    }
    @Override
    public int getCount() {
        return this.categorias.size();
    }

    @Override
    public Object getItem(int i) {
        return this.categorias.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        Log.i("Entre",position+"");
        v = convertView;
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        v = layoutInflater.inflate(R.layout.item_catalogo, null);
        //ImageView imageView;
        //CardView card = v.findViewById(R.id.cardCategoria);

        ImageView img =  v.findViewById(R.id.categoriaImagen);
        //int imgPosition= ActivityCatalogo..indexOf(currentName);
        Glide.with(context.getApplicationContext()).load(categorias.get(position).getImagenRuta()).into(img);
        //card.addView(img);
        return v;
    }
}



