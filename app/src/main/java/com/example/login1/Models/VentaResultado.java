package com.example.login1.Models;

import java.util.Date;

public class VentaResultado {
    private String Id;
    private String Vendedor;
    private String Cliente;
    private String Surtidor;
    private String FechaVenta;
    private String FechaProgramadaEntrega;
    private String FechaEntrega;
    private String Total;
    private String Iva;
    private String Domicilio;
    private String FirmaRuta;
    private String TipoVenta;
    private String EstadoVenta;
    private String Estado;

    public  VentaResultado()
    {

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getVendedor() {
        return Vendedor;
    }

    public void setVendedor(String vendedor) {
        Vendedor = vendedor;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String cliente) {
        Cliente = cliente;
    }

    public String getSurtidor() {
        return Surtidor;
    }

    public void setSurtidor(String surtidor) {
        Surtidor = surtidor;
    }

    public String getFechaVenta() {
        return FechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        FechaVenta = fechaVenta;
    }

    public String getFechaProgramadaEntrega() {
        return FechaProgramadaEntrega;
    }

    public void setFechaProgramadaEntrega(String fechaProgramadaEntrega) {
        FechaProgramadaEntrega = fechaProgramadaEntrega;
    }

    public String getFechaEntrega() {
        return FechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        FechaEntrega = fechaEntrega;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getIva() {
        return Iva;
    }

    public void setIva(String iva) {
        Iva = iva;
    }

    public String getDomicilio() {
        return Domicilio;
    }

    public void setDomicilio(String domicilio) {
        Domicilio = domicilio;
    }

    public String getFirmaRuta() {
        return FirmaRuta;
    }

    public void setFirmaRuta(String firmaRuta) {
        FirmaRuta = firmaRuta;
    }

    public String getTipoVenta() {
        return TipoVenta;
    }

    public void setTipoVenta(String tipoVenta) {
        TipoVenta = tipoVenta;
    }

    public String getEstadoVenta() {
        return EstadoVenta;
    }

    public void setEstadoVenta(String estadoVenta) {
        EstadoVenta = estadoVenta;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }



}