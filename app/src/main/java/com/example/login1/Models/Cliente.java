package com.example.login1.Models;


public class Cliente {
    private  String Id;
    private String Nombre;
    private String ApellidoP;
    private String ApellidoM;
    private String Telefono;
    private String Email;
    private String Localidad;
    private String Colonia;
    private String Calle;
    private String NumeroIntoExt;
    private String Estado;

    public Cliente()
    {

    }
    public Cliente(String Id,String Nombre,String ApellidoP,String ApellidoM,String Telefono,String Email,String Localidad,String Colonia,String Calle,String NumeroIntoExt,String Estado)
    {
        this.Id = Id;
        this.Nombre = Nombre;
        this.ApellidoP = ApellidoP;
        this.ApellidoM = ApellidoM;
        this.Telefono = Telefono;
        this.Email = Email;
        this.Localidad = Localidad;
        this.Colonia = Colonia;
        this.Calle = Calle;
        this.NumeroIntoExt = NumeroIntoExt;
        this.Estado = Estado;
    }
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidoP() {
        return ApellidoP;
    }

    public void setApellidoP(String apellidoP) {
        ApellidoP = apellidoP;
    }

    public String getApellidoM() {
        return ApellidoM;
    }

    public void setApellidoM(String apellidoM) {
        ApellidoM = apellidoM;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getLocalidad() {
        return Localidad;
    }

    public void setLocalidad(String localidad) {
        Localidad = localidad;
    }

    public String getColonia() {
        return Colonia;
    }

    public void setColonia(String colonia) {
        Colonia = colonia;
    }

    public String getCalle() {
        return Calle;
    }

    public void setCalle(String calle) {
        Calle = calle;
    }

    public String getNumeroIntoExt() {
        return NumeroIntoExt;
    }

    public void setNumeroIntoExt(String numeroIntoExt) {
        NumeroIntoExt = numeroIntoExt;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    @Override
    public String toString() {
        return Email;
    }

}
