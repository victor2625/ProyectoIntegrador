package com.serranito.controller;

import com.serranito.dao.*;
import com.serranito.model.*;
import java.time.LocalDateTime;
import java.util.List;

public class VentaController {
    private VentaDAO ventaDAO = new VentaDAOImpl();
    private DetalleVentaDAO detalleDAO = new DetalleVentaDAOImpl();
    private EnvaseDAO envaseDAO = new EnvaseDAOImpl();

    public void registrarVenta(List<DetalleVenta> detalles) {
        double total = detalles.stream().mapToDouble(DetalleVenta::getSubtotal).sum();
        Venta venta = new Venta(LocalDateTime.now(), total);
        ventaDAO.registrarVenta(venta);

        for (DetalleVenta detalle : detalles) {
            detalle.setIdVenta(venta.getId());
            detalleDAO.agregarDetalle(detalle);
            Envase envase = detalle.getEnvase();
            envase.setStock(envase.getStock() - detalle.getCantidad());
            envaseDAO.actualizar(envase);
        }
    }

    public List<Venta> listarVentas() {
        return ventaDAO.listarTodas();
    }
}