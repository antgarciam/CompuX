package com.cliente.usuario.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import com.cliente.usuario.model.Usuario;
import com.cliente.usuario.service.UsuarioService;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;


@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc; 

    @MockitoBean 
    private UsuarioService service;

    @Test
    void listarUsuarios() throws Exception {
    
        when(service.listar()).thenReturn(List.of(new Usuario()));
    
        mockMvc.perform(get("/usuario/listar"))
                .andExpect(status().isOk());
    }

    @Test
    void listarUsuariosDTO() throws Exception {

        when(service.listarDTO()).thenReturn(List.of());

        mockMvc.perform(get("/usuario/listar-dto"))
                .andExpect(status().isOk());
    }

    @Test
    void buscarUsuarioPorId() throws Exception {

        when(service.buscarPorId(1))
                .thenReturn(Optional.of(new Usuario()));

        mockMvc.perform(get("/usuario/buscar/1"))
                .andExpect(status().isOk());
    }

    @Test
    void buscarPorNombre() throws Exception {

        when(service.buscarPorNombreUsuario("Carlos"))
                .thenReturn(List.of(new Usuario()));

        mockMvc.perform(get("/usuario/nombre/Carlos"))
                .andExpect(status().isOk());
    }

    @Test
    void agregarUsuario() throws Exception {

        String usuarioJson = """
        {
            "nombreUsuario":"Carlos",
            "apellidoUsuario":"Paredes",
            "correoUsuario":"carlos@gmail.com"
        }
        """;

        Usuario usuario = new Usuario(
                1,
                "Carlos",
                "Paredes",
                "carlos@gmail.com"
        );

        when(service.guardar(any(Usuario.class)))
                .thenReturn(usuario);

        mockMvc.perform(post("/usuario/agregar")
                .contentType(APPLICATION_JSON)
                .content(usuarioJson))
                .andExpect(status().isOk());
    }


    @Test
    void verCatalogo() throws Exception {

        when(service.obtenerCatalogoDeProductos())
                .thenReturn(List.of());

        mockMvc.perform(get("/usuario/catalogo"))
                .andExpect(status().isOk());
    }

}
