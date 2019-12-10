package com.example.login1.Adapters;
import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.login1.Models.Categoria;
import com.example.login1.Models.Producto;
import com.example.login1.Models.VentaDetalleResultado;
import com.example.login1.R;
import java.util.List;
public  class AdaptadorProductosCategoria extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Producto> productos;
    private View v;
    private LinearLayout l;
    public AdaptadorProductosCategoria(Context context, int layout, List<Producto> productos) {
        this.context = context;
        this.layout = layout;
        this.productos = productos;

    }
    @Override
    public int getCount() {
        return this.productos.size();
    }

    @Override
    public Object getItem(int i) {
        return this.productos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        v = convertView;
        LayoutInflater layoutInflater=LayoutInflater.from(this.context);
        v=layoutInflater.inflate(R.layout.item_productoscategoria,null);

        //productoActual = names.get(position).getNombre();

        TextView nombre =v.findViewById(R.id.nombreProductoCategoria);
        nombre.setText(productos.get(position).getNombre());

        //TextView textStoc =v.findViewById(R.id.stock);
        //textStoc.setText("Stock: "+String.valueOf(names.get(position).getStock()));
        TextView precioMayoreo = v.findViewById(R.id.precioMayoreoProductoCategoria);
        precioMayoreo.setText("Precio Mayoreo: "+productos.get(position).getPrecioMayoreo());
        TextView precioMenudeo = v.findViewById(R.id.precioMenudeoProductoCategoria);
        precioMenudeo.setText("Precio Menudeo: "+productos.get(position).getPrecioMenudeo());

        TextView stock = v.findViewById(R.id.stockProductoCategoria);
        stock.setText("Stock: "+productos.get(position).getStock());


        ImageView img =  v.findViewById(R.id.imagenProductoCategoria);
        //int imgPosition= ActivityProductosCategoria.prod.indexOf(productos.get(position).getNombre());
        Glide.with(context.getApplicationContext()).load(productos.get(position).getImagenRuta()).into(img);
        return v;
    }
}



