package com.resenas.comentario.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.resenas.comentario.controller.ComentarioControlador;
import com.resenas.comentario.dto.ComentarioRequestDTO;
import com.resenas.comentario.dto.ComentarioResponseDTO;
import com.resenas.comentario.model.Comentario;
import com.resenas.comentario.service.ComentarioServicio;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.mockito.ArgumentMatchers.any;


@WebMvcTest(ComentarioControlador.class)
public class ComentarioControllerTest {

    @Autowired
    private MockMvc mockMvc; 

    @MockitoBean 
    private ComentarioServicio service;

    @Test
    void listarComentarios() throws Exception {

        when(service.listar()).thenReturn(List.of(new Comentario()));

        mockMvc.perform(get("/resenas/listar"))
                .andExpect(status().isOk());
    }

    @Test
    void buscarComentarioPorId() throws Exception {

        when(service.buscarPorId(1))
                .thenReturn(Optional.of(new Comentario()));

        mockMvc.perform(get("/resenas/buscar/1"))
                .andExpect(status().isOk());
    }

    @Test
    void agregarComentario() throws Exception {

        String comentarioJson = """
        {
            "usuarioId":1,
            "productoId":1,
            "comentario":"Muy buen producto"
        }
        """;

        when(service.crearComentario(any(ComentarioRequestDTO.class)))
                .thenReturn(new ComentarioResponseDTO());

        mockMvc.perform(post("/resenas/agregar")
                .contentType(APPLICATION_JSON)
                .content(comentarioJson))
                .andExpect(status().isOk());
    }
}