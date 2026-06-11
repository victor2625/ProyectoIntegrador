package com.serranito.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.serranito.dao.DetalleVentaDAO;
import com.serranito.dao.DetalleVentaDAOImpl;
import com.serranito.dao.EnvaseDAO;
import com.serranito.dao.EnvaseDAOImpl;
import com.serranito.dao.VentaDAO;
import com.serranito.dao.VentaDAOImpl;
import com.serranito.model.DetalleVenta;
import com.serranito.model.Envase;
import com.serranito.model.Venta;

public class VentaController {
    private VentaDAO ventaDAO = new VentaDAOImpl();
    private DetalleVentaDAO detalleDAO = new DetalleVentaDAOImpl();
    private EnvaseDAO envaseDAO = new EnvaseDAOImpl();

    public void registrarVenta(List<DetalleVenta> detalles) {
        // Calcular total
        double total = detalles.stream().mapToDouble(DetalleVenta::getSubtotal).sum();
        Venta venta = new Venta(LocalDateTime.now(), total);
        ventaDAO.registrarVenta(venta);

        // Registrar detalles y actualizar stock
        for (DetalleVenta detalle : detalles) {
            detalle.setIdVenta(venta.getId());
            detalleDAO.agregarDetalle(detalle);
            // Actualizar stock
            Envase envase = detalle.getEnvase();
            int nuevoStock = envase.getStock() - detalle.getCantidad();
            envase.setStock(nuevoStock);
            envaseDAO.actualizar(envase);
        }
    }

    public List<Venta> listarVentas() {
        return ventaDAO.listarTodas();
    }
}