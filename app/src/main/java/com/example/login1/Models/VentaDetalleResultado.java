package com.example.login1.Models;

public class VentaDetalleResultado {
    public String Nombre ;
    public String Imagen ;
    public int Cantidad;
    public String Precio;
    public String Importe;

    public VentaDetalleResultado()
    {

    }
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    public String getImporte() {
        return Importe;
    }

    public void setImporte(String importe) {
        Importe = importe;
    }
}
