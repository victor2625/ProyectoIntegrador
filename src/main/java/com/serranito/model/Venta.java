package com.serranito.model;

import java.time.LocalDateTime;

public class Venta {
    private int id;
    private LocalDateTime fechaVenta;
    private double totalVenta;

    public Venta() {}

    public Venta(LocalDateTime fechaVenta, double totalVenta) {
        this.fechaVenta = fechaVenta;
        this.totalVenta = totalVenta;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public LocalDateTime getFechaVenta() { return fechaVenta; }
    public void setFechaVenta(LocalDateTime fechaVenta) { this.fechaVenta = fechaVenta; }
    public double getTotalVenta() { return totalVenta; }
    public void setTotalVenta(double totalVenta) { this.totalVenta = totalVenta; }
}