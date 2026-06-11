package com.serranito.dao;

public interface UsuarioDAO {
    boolean validarCredenciales(String nombre, String contrasena);
}