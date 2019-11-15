package com.example.login1.Activities;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.login1.Models.Producto;
import com.example.login1.R;

import java.util.ArrayList;
import java.util.List;

public  class MyAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Producto> names;
    private EditText cant;
    private View v;
    private String currentName;

    public MyAdapter(Context context, int layout, List<Producto> names) {
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
    public View getView(final int position, View convertView, ViewGroup viewGroup) {

        v = convertView;

        LayoutInflater layoutInflater=LayoutInflater.from(this.context);
        v=layoutInflater.inflate(R.layout.list_item,null);

        currentName = names.get(position).getNombre();

        TextView textView =(TextView) v.findViewById(R.id.textView);
        textView.setText(currentName);

        ImageView img = (ImageView) v.findViewById(R.id.imageView);
        int imgPosition= ActivityVendedor.prod.indexOf(currentName);
        Glide.with(context.getApplicationContext()).load(ActivityVendedor.productos.get(imgPosition).getImagenRuta()).into(img);



        mostrarPrecios(position);

        posisiones(ActivityVendedor.selectedProd);
        cant.addTextChangedListener(
                new TextWatcher() {
                    @Override public void onTextChanged(CharSequence s, int start, int before, int count) { }
                    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                    @Override
                    public void afterTextChanged(final Editable s) {
                        try {
                            ActivityVendedor.selectedProd.get(position).setPedidos(Integer.parseInt(cant.getText().toString()));
                            ActivityVendedor.txTotal.setText("Total: $ "+ActivityVendedor.calcularTotal(ActivityVendedor.productos,ActivityVendedor.selectedProd));
                        }catch (Exception e){
                        }
                        mostrarPrecios(position);

                    }
                }
        );



        return v;
    }
    private void mostrarPrecios(int position){
        TextView precioArticulo= v.findViewById(R.id.textViewPrecio);
        for (Producto item:ActivityVendedor.productos ){
            if (item.getNombre().equals(currentName)){
                if (ActivityVendedor.selectedProd.get(position).getPedidos()>2){
                    precioArticulo.setText("Precio: $"+String.valueOf(item.getPrecioMayoreo()));
                }else
                    precioArticulo.setText("Precio: $"+String.valueOf(item.getPrecioMenudeo()));

            }
        }
    }
    public void posisiones(ArrayList<Producto> prod){
        for (Producto item:prod){
            Toast.makeText(context,String.valueOf(item.getPedidos()),Toast.LENGTH_SHORT).show();
        }
    }

}
