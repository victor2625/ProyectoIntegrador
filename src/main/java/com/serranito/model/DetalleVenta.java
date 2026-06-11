package com.serranito.model;

public class DetalleVenta {
    private int id;
    private int idVenta;
    private Envase envase;
    private int cantidad;
    private double subtotal;

    public DetalleVenta() {}

    public DetalleVenta(Envase envase, int cantidad, double subtotal) {
        this.envase = envase;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }
    public Envase getEnvase() { return envase; }
    public void setEnvase(Envase envase) { this.envase = envase; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
}