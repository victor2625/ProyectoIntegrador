package com.serranito.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serranito.model.Envase;
import com.serranito.util.DatabaseConnection;

public class EnvaseDAOImpl implements EnvaseDAO {
    private static final Logger logger = LoggerFactory.getLogger(EnvaseDAOImpl.class);
    private Connection conn = DatabaseConnection.getInstance().getConnection();

    @Override
    public void agregar(Envase envase) {
        String sql = "INSERT INTO envases (tipo, capacidad, material, stock, precio_unidad) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, envase.getTipo());
            ps.setString(2, envase.getCapacidad());
            ps.setString(3, envase.getMaterial());
            ps.setInt(4, envase.getStock());
            ps.setDouble(5, envase.getPrecioUnidad());
            ps.executeUpdate();
            logger.info("Envase agregado: {}", envase.getTipo());
        } catch (SQLException e) {
            logger.error("Error al agregar envase", e);
        }
    }

    @Override
    public void actualizar(Envase envase) {
        String sql = "UPDATE envases SET tipo=?, capacidad=?, material=?, stock=?, precio_unidad=? WHERE id_envase=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, envase.getTipo());
            ps.setString(2, envase.getCapacidad());
            ps.setString(3, envase.getMaterial());
            ps.setInt(4, envase.getStock());
            ps.setDouble(5, envase.getPrecioUnidad());
            ps.setInt(6, envase.getId());
            ps.executeUpdate();
            logger.info("Envase actualizado: {}", envase.getTipo());
        } catch (SQLException e) {
            logger.error("Error al actualizar envase", e);
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM envases WHERE id_envase=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            logger.info("Envase eliminado con id: {}", id);
        } catch (SQLException e) {
            logger.error("Error al eliminar envase", e);
        }
    }

    @Override
    public Envase buscarPorId(int id) {
        String sql = "SELECT * FROM envases WHERE id_envase=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Envase e = new Envase();
                e.setId(rs.getInt("id_envase"));
                e.setTipo(rs.getString("tipo"));
                e.setCapacidad(rs.getString("capacidad"));
                e.setMaterial(rs.getString("material"));
                e.setStock(rs.getInt("stock"));
                e.setPrecioUnidad(rs.getDouble("precio_unidad"));
                return e;
            }
        } catch (SQLException e) {
            logger.error("Error al buscar envase por id", e);
        }
        return null;
    }

    @Override
    public List<Envase> listarTodos() {
        List<Envase> lista = new ArrayList<>();
        String sql = "SELECT * FROM envases";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Envase e = new Envase();
                e.setId(rs.getInt("id_envase"));
                e.setTipo(rs.getString("tipo"));
                e.setCapacidad(rs.getString("capacidad"));
                e.setMaterial(rs.getString("material"));
                e.setStock(rs.getInt("stock"));
                e.setPrecioUnidad(rs.getDouble("precio_unidad"));
                lista.add(e);
            }
        } catch (SQLException e) {
            logger.error("Error al listar envases", e);
        }
        return lista;
    }
}