package com.serranito.dao;

import java.util.List;

import com.serranito.model.Envase;

public interface EnvaseDAO {
    void agregar(Envase envase);
    void actualizar(Envase envase);
    void eliminar(int id);
    Envase buscarPorId(int id);
    List<Envase> listarTodos();
}
