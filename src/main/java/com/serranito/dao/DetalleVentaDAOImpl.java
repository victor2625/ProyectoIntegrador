package com.serranito.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serranito.model.DetalleVenta;
import com.serranito.util.DatabaseConnection;

public class DetalleVentaDAOImpl implements DetalleVentaDAO {
    private static final Logger logger = LoggerFactory.getLogger(DetalleVentaDAOImpl.class);
    private Connection conn = DatabaseConnection.getInstance().getConnection();

    @Override
    public void agregarDetalle(DetalleVenta detalle) {
        String sql = "INSERT INTO detalle_venta (id_venta, id_envase, cantidad, subtotal) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, detalle.getIdVenta());
            ps.setInt(2, detalle.getEnvase().getId());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getSubtotal());
            ps.executeUpdate();
            logger.info("Detalle agregado para venta ID: {}", detalle.getIdVenta());
        } catch (SQLException e) {
            logger.error("Error al agregar detalle", e);
        }
    }
}