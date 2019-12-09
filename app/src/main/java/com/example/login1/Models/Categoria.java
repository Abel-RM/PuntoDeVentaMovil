package com.example.login1.Models;

public class Categoria {
    private String Nombre;
    private String ImagenRuta;
    public Categoria()
    {

    }
    public String getNombre(){
        return  this.Nombre;
    }
    public void setNombre(String Nombre)
    {
        this.Nombre =Nombre;
    }
    public String getImagenRuta(){
        return  this.ImagenRuta;
    }
    public void setImagenRuta(String ImagenRuta)
    {
        this.ImagenRuta =ImagenRuta;
    }
}
