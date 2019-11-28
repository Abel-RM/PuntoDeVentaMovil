package com.example.login1.Models;

import java.io.File;
import java.util.Date;
import java.util.List;

public class Venta
{
    private String Id;
    private String SurtidorId;
    private String VendedorId;
    private String ClienteId;
    private Date FechaVenta;
    private Date FechaProgramadaEntrega;
    private Date FechaEntrega;
    private List<VentaDetalle> VentaDetalle;
    private double Total;
    private double PagoEfectivo;
    private double Cambio;
    private String FirmaRuta;
    private File Firma;
    private String TipoVenta; //Pedido y VentaSucursal
    private String EstadoVenta;//Entregado y No Entregado

    public Venta(String vendedorId, String clienteId, List<com.example.login1.Models.VentaDetalle> ventaDetalle, double total, double pagoEfectivo, double cambio, String tipoVenta) {
        VendedorId = vendedorId;
        ClienteId = clienteId;
        VentaDetalle = ventaDetalle;
        Total = total;
        PagoEfectivo = pagoEfectivo;
        Cambio = cambio;
        TipoVenta = tipoVenta;
    }

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

    public Date getFechaVenta() {
        return FechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        FechaVenta = fechaVenta;
    }

    public Date getFechaProgramadaEntrega() {
        return FechaProgramadaEntrega;
    }

    public void setFechaProgramadaEntrega(Date fechaProgramadaEntrega) {
        FechaProgramadaEntrega = fechaProgramadaEntrega;
    }

    public Date getFechaEntrega() {
        return FechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        FechaEntrega = fechaEntrega;
    }

    public List<com.example.login1.Models.VentaDetalle> getVentaDetalle() {
        return VentaDetalle;
    }

    public void setVentaDetalle(List<com.example.login1.Models.VentaDetalle> ventaDetalle) {
        VentaDetalle = ventaDetalle;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public double getPagoEfectivo() {
        return PagoEfectivo;
    }

    public void setPagoEfectivo(double pagoEfectivo) {
        PagoEfectivo = pagoEfectivo;
    }

    public double getCambio() {
        return Cambio;
    }

    public void setCambio(double cambio) {
        Cambio = cambio;
    }

    public String getFirmaRuta() {
        return FirmaRuta;
    }

    public void setFirmaRuta(String firmaRuta) {
        FirmaRuta = firmaRuta;
    }

    public File getFirma() {
        return Firma;
    }

    public void setFirma(File firma) {
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