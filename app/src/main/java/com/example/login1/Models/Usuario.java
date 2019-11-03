package com.example.login1.Models;

public class Usuario{
    private String Id;
    private String Nombre;
    private String ApellidoP;
    private String ApellidoM;
    private String Email;
    private String Password;
    private String Rol;
    private String Imagen;
    private String ImagenRuta;
    private String Telefono;
    private String Rfc;
    private String Estado;
    private String Token;

    public Usuario(String Id, String Nombre, String ApellidoP, String ApellidoM, String Email, String Password, String Rol, String Imagen, String ImagenRuta, String Telefono, String Rfc, String Estado, String Token) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.ApellidoP = ApellidoP;
        this.ApellidoM = ApellidoM;
        this.Email = Email;
        this.Password = Password;
        this.Rol = Rol;
        this.Imagen = Imagen;
        this.ImagenRuta = ImagenRuta;
        this.Telefono = Telefono;
        this.Rfc = Rfc;
        this.Estado = Estado;
        this.Token = Token;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String rol) {
        Rol = rol;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }

    public String getImagenRuta() {
        return ImagenRuta;
    }

    public void setImagenRuta(String imagenRuta) {
        ImagenRuta = imagenRuta;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getRfc() {
        return Rfc;
    }

    public void setRfc(String rfc) {
        Rfc = rfc;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
