package com.serranito.dao;

import com.serranito.model.Envase;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class EnvaseDAOTest {
    static EnvaseDAO envaseDAO;

    @BeforeAll
    static void setup() {
        envaseDAO = new EnvaseDAOImpl();
    }

    @Test
    void testAgregarYListar() {
        Envase e = new Envase("Prueba", "10L", "Plástico", 5, 30.0);
        envaseDAO.agregar(e);
        assertTrue(envaseDAO.listarTodos().size() > 0);
    }

    @Test
    void testBuscarPorId() {
        // Primero insertamos uno
        Envase e = new Envase("TestBuscar", "5L", "Metal", 3, 45.0);
        envaseDAO.agregar(e);
        // Buscamos el último (asumimos que el último insertado tiene ID mayor)
        var lista = envaseDAO.listarTodos();
        int ultimoId = lista.get(lista.size() - 1).getId();
        Envase encontrado = envaseDAO.buscarPorId(ultimoId);
        assertNotNull(encontrado);
        assertEquals("TestBuscar", encontrado.getTipo());
    }
}