package com.example.login1.Models;

public class VentaDetalle
{
    private String ProductoId;
    private String Cantidad;
    private String Precio;
    private String  Importe;
    public VentaDetalle(){

    }
    public String getProductoId() {
        return ProductoId;
    }

    public void setProductoId(String productoId) {
        ProductoId = productoId;
    }

    public String  getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String cantidad) {
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
