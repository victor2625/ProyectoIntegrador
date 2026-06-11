package com.serranito.model;

public class Envase {
    private int id;
    private String tipo;
    private String capacidad;
    private String material;
    private int stock;
    private double precioUnidad;

    public Envase() {}

    public Envase(String tipo, String capacidad, String material, int stock, double precioUnidad) {
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.material = material;
        this.stock = stock;
        this.precioUnidad = precioUnidad;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getCapacidad() { return capacidad; }
    public void setCapacidad(String capacidad) { this.capacidad = capacidad; }
    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public double getPrecioUnidad() { return precioUnidad; }
    public void setPrecioUnidad(double precioUnidad) { this.precioUnidad = precioUnidad; }
}
