package com.serranito.controller;

import org.apache.commons.lang3.StringUtils;

import com.serranito.dao.UsuarioDAO;
import com.serranito.dao.UsuarioDAOImpl;

public class LoginController {
    private UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    public boolean autenticar(String usuario, String password) {
    System.out.println("Intentando autenticar: " + usuario);
    if (StringUtils.isBlank(usuario) || StringUtils.isBlank(password)) {
        System.out.println("Usuario o contraseña vacíos");
        return false;
    }
    boolean resultado = usuarioDAO.validarCredenciales(usuario, password);
    System.out.println("Resultado autenticación: " + resultado);
    return resultado;
}
}
