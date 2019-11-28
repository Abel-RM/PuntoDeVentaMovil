package com.example.login1.Activities;
import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.login1.Models.Producto;
import com.example.login1.R;
import java.util.ArrayList;
import java.util.List;
import static com.example.login1.Activities.ActivityVendedor.list;
import static com.example.login1.Activities.ActivityVendedor.selectedProd;

public  class MyAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Producto> names;
    private EditText cant;
    private View v;
    private String currentName;
    private LinearLayout l;



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

        TextView textView =v.findViewById(R.id.textView);
        textView.setText(currentName);

        TextView textStoc =v.findViewById(R.id.stock);
        textStoc.setText("Stock: "+String.valueOf(names.get(position).getStock()));

        l= v.findViewById(R.id.listItem);
        l.setId(Integer.parseInt("2"+String.valueOf(position)+"74"));
        OnSwipeListener listener = new OnSwipeListener(context,v,position);
        l.setOnTouchListener(listener);

        ImageView img =  v.findViewById(R.id.imageView);
        int imgPosition= ActivityVendedor.prod.indexOf(currentName);
        Glide.with(context.getApplicationContext()).load(ActivityVendedor.productos.get(imgPosition).getImagenRuta()).into(img);


        cant=v.findViewById(R.id.editText);
        cant.setId(position);

        EditText tex=v.findViewById(position);

        mostrarPrecios(currentName);
        posisiones(selectedProd,position);



        tex.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                try {
                    EditText tx =view.findViewById(view.getId());
                    int stock = selectedProd.get(view.getId()).getStock();
                    int pedidos = Integer.parseInt(tx.getText().toString());
                    if (pedidos > stock+100){
                        selectedProd.get(view.getId()).setPedidos(stock);
                        tx.setText(String.valueOf(stock));
                    }else{
                        selectedProd.get(view.getId()).setPedidos(Integer.parseInt(tx.getText().toString()));
                    }
                    ActivityVendedor.txTotal.setText("Total: $ "+ActivityVendedor.calcularTotal(ActivityVendedor.productos, selectedProd));

                }catch (Exception e){ }

                return false;
            }
        });

        return v;
    }


    private void posisiones(ArrayList<Producto> prod,int position){
        EditText tx;
        tx=v.findViewById(position);
        tx.setText(String.valueOf(prod.get(position).getPedidos()));

    }


    private void mostrarPrecios(String name){
        TextView precioArticulo= v.findViewById(R.id.textViewPrecio);
        Producto p = buscarProducto(ActivityVendedor.productos,name);

        precioArticulo.setText("Normal: $"+String.valueOf(p.getPrecioMenudeo())+"\t\tMayoreo: "+String.valueOf(p.getPrecioMayoreo()));


    }

    private Producto buscarProducto(ArrayList<Producto> lista,String producto){
        for(Producto item: lista){
            if (item.getNombre().equals(producto)){
                return item;
            }
        }
        return null;
    }

}



