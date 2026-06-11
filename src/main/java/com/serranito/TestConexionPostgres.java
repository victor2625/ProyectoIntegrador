package com.serranito;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestConexionPostgres {
    public static void main(String[] args) {
        // Cambia estos datos si es necesario
        String url = "jdbc:postgresql://localhost:5432/postgres";  // conéctate a la BD por defecto 'postgres'
        String user = "postgres";
        String password = "123VICTOR"; // tu contraseña

        System.out.println("Conectando al servidor PostgreSQL...");
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("✅ Conectado al servidor!");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT datname FROM pg_database WHERE datistemplate = false;");
            System.out.println("Bases de datos en este servidor:");
            while (rs.next()) {
                System.out.println("  - " + rs.getString("datname"));
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}