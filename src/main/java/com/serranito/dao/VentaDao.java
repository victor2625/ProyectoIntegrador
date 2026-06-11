package com.serranito.dao;

import com.serranito.model.Venta;
import java.util.List;

public interface VentaDAO {
    void registrarVenta(Venta venta);
    List<Venta> listarTodas();
}