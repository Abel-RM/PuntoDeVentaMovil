package com.example.login1.Models;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Venta
{
    private String Id;
    private String SurtidorId;
    private String VendedorId;
    private String ClienteId;
    private String FechaVenta;
    private String FechaProgramadaEntrega;
    private String FechaEntrega;
    private ArrayList<VentaDetalle> VentaDetalle;
    private String Total;
    private String PagoEfectivo;
    private String Cambio;
    private String FirmaRuta;
    private String Firma;
    private String TipoVenta; //Pedido y VentaSucursal
    private String EstadoVenta;//Entregado y No Entregado



    public Venta()
    {

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getSurtidorId() {
        return SurtidorId;
    }

    public void setSurtidorId(String surtidorId) {
        SurtidorId = surtidorId;
    }

    public String getVendedorId() {
        return VendedorId;
    }

    public void setVendedorId(String vendedorId) {
        VendedorId = vendedorId;
    }

    public String getClienteId() {
        return ClienteId;
    }

    public void setClienteId(String clienteId) {
        ClienteId = clienteId;
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

    public ArrayList<VentaDetalle> getVentaDetalle() {
        return VentaDetalle;
    }

    public void setVentaDetalle(ArrayList<VentaDetalle> ventaDetalle) {
        VentaDetalle = ventaDetalle;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getPagoEfectivo() {
        return PagoEfectivo;
    }

    public void setPagoEfectivo(String pagoEfectivo) {
        PagoEfectivo = pagoEfectivo;
    }

    public String getCambio() {
        return Cambio;
    }

    public void setCambio(String cambio) {
        Cambio = cambio;
    }

    public String getFirmaRuta() {
        return FirmaRuta;
    }

    public void setFirmaRuta(String firmaRuta) {
        FirmaRuta = firmaRuta;
    }

    public String getFirma() {
        return Firma;
    }

    public void setFirma(String firma) {
        Firma = firma;
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
}