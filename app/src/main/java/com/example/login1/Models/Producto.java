package com.example.login1.Models;

public class Producto {
    private String Id;
    private String CodigoBarra;
    private String Nombre;
    private String Descripcion;
    private int Stock;
    private int Pedidos;
    private int PrecioMayoreo;
    private int PrecioMenudeo;
    private String ImagenRuta;
    private String Categoria;
    private String Estado;
    private String Imagen;


    public Producto(String Id, String CodigoBarra, String Nombre, String Descripcion, int Stock, int Pedidos, int PrecioMayoreo, int PrecioMenudeo, String ImagenRuta, String Categoria, String Estado, String Imagen) {
        this.Id = Id;
        this.CodigoBarra = CodigoBarra;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.Stock = Stock;
        this.Pedidos = Pedidos;
        this.PrecioMayoreo = PrecioMayoreo;
        this.PrecioMenudeo = PrecioMenudeo;
        this.ImagenRuta = ImagenRuta;
        this.Categoria = Categoria;
        this.Estado = Estado;
        this.Imagen = Imagen;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCodigoBarra() {
        return CodigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        CodigoBarra = codigoBarra;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public int getPedidos() {
        return Pedidos;
    }

    public void setPedidos(int pedidos) {
        Pedidos = pedidos;
    }

    public int getPrecioMayoreo() {
        return PrecioMayoreo;
    }

    public void setPrecioMayoreo(int precioMayoreo) {
        PrecioMayoreo = precioMayoreo;
    }

    public int getPrecioMenudeo() {
        return PrecioMenudeo;
    }

    public void setPrecioMenudeo(int precioMenudeo) {
        PrecioMenudeo = precioMenudeo;
    }

    public String getImagenRuta() {
        return ImagenRuta;
    }

    public void setImagenRuta(String imagenRuta) {
        ImagenRuta = imagenRuta;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }
}
