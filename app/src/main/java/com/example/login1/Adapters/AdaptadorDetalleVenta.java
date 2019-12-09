package com.example.login1.Adapters;
import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.login1.Models.VentaDetalleResultado;
import com.example.login1.R;
import java.util.List;
public  class AdaptadorDetalleVenta extends BaseAdapter {

    private Context context;
    private int layout;
    private List<VentaDetalleResultado> ventaDetalles;
    private View v;
    private LinearLayout l;



    public AdaptadorDetalleVenta(Context context, int layout, List<VentaDetalleResultado> ventaDetalles) {
        this.context = context;
        this.layout = layout;
        this.ventaDetalles = ventaDetalles;

    }
    @Override
    public int getCount() {
        return this.ventaDetalles.size();
    }

    @Override
    public Object getItem(int i) {
        return this.ventaDetalles.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        v = convertView;
        LayoutInflater layoutInflater=LayoutInflater.from(this.context);
        v=layoutInflater.inflate(R.layout.item_detalleventa,null);


        TextView nombre =v.findViewById(R.id.nombreProducto);
        nombre.setText(ventaDetalles.get(position).getNombre());

        TextView precio = v.findViewById(R.id.precioProducto);
        precio.setText("Precio: "+ventaDetalles.get(position).getPrecio());

        TextView cantidad = v.findViewById(R.id.cantidadProducto);
        cantidad.setText("Cantidad: "+ventaDetalles.get(position).getCantidad());

        TextView importe = v.findViewById(R.id.importeVenta);
        importe.setText("Importe: "+ventaDetalles.get(position).getImporte());


        ImageView img =  v.findViewById(R.id.imagenProducto);
        Glide.with(context.getApplicationContext()).load(ventaDetalles.get(position).getImagen()).into(img);

        return v;
    }
}



