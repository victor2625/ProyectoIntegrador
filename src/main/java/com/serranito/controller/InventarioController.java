package com.serranito.controller;

import java.util.List;

import com.serranito.dao.EnvaseDAO;
import com.serranito.dao.EnvaseDAOImpl;
import com.serranito.model.Envase;

public class InventarioController {
    private EnvaseDAO envaseDAO = new EnvaseDAOImpl();

    public List<Envase> listarEnvases() {
        return envaseDAO.listarTodos();
    }

    public void agregarEnvase(Envase envase) {
        envaseDAO.agregar(envase);
    }

    public void actualizarEnvase(Envase envase) {
        envaseDAO.actualizar(envase);
    }

    public void eliminarEnvase(int id) {
        envaseDAO.eliminar(id);
    }
}