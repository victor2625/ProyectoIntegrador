package com.serranito.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serranito.util.DatabaseConnection;

public class UsuarioDAOImpl implements UsuarioDAO {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioDAOImpl.class);
    private Connection conn = DatabaseConnection.getInstance().getConnection();

    @Override
    public boolean validarCredenciales(String nombre, String contrasena) {
    String sql = "SELECT * FROM usuarios WHERE nombre = ? AND contrasena = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, nombre);
        ps.setString(2, contrasena);
        ResultSet rs = ps.executeQuery();
        boolean existe = rs.next();
        System.out.println("Usuario encontrado: " + existe);
        return existe;
    } catch (SQLException e) {
        System.err.println("Error SQL: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}
}
