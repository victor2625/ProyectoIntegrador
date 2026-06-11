package com.serranito.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serranito.model.Venta;
import com.serranito.util.DatabaseConnection;

public class VentaDAOImpl implements VentaDAO {
    private static final Logger logger = LoggerFactory.getLogger(VentaDAOImpl.class);
    private Connection conn = DatabaseConnection.getInstance().getConnection();

    @Override
    public void registrarVenta(Venta venta) {
        String sql = "INSERT INTO ventas (fecha_venta, total_venta) VALUES (?, ?) RETURNING id_venta";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(venta.getFechaVenta()));
            ps.setDouble(2, venta.getTotalVenta());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                venta.setId(rs.getInt(1));
                logger.info("Venta registrada con ID: {}", venta.getId());
            }
        } catch (SQLException e) {
            logger.error("Error al registrar venta", e);
        }
    }

    @Override
    public List<Venta> listarTodas() {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM ventas ORDER BY id_venta DESC";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Venta v = new Venta();
                v.setId(rs.getInt("id_venta"));
                v.setFechaVenta(rs.getTimestamp("fecha_venta").toLocalDateTime());
                v.setTotalVenta(rs.getDouble("total_venta"));
                lista.add(v);
            }
        } catch (SQLException e) {
            logger.error("Error al listar ventas", e);
        }
        return lista;
    }
}