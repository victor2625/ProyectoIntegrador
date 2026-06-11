package com.serranito.dao;

import java.util.List;
import com.serranito.model.Venta;

public interface VentaDAO {
    void registrarVenta(Venta venta);
    List<Venta> listarTodas();
}