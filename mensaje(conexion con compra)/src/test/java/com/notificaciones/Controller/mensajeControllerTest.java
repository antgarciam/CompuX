package com.notificaciones.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.notificaciones.mensaje.Controller.mensajeController;
import com.notificaciones.mensaje.DTO.mensajeDTO;
import com.notificaciones.mensaje.Model.mensajeModel;
import com.notificaciones.mensaje.Service.mensajeService;

import java.time.LocalDateTime;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.mockito.ArgumentMatchers.any;


@WebMvcTest(mensajeController.class)
public class mensajeControllerTest {

    @Autowired
    private MockMvc mockMvc; 

    @MockitoBean
    private mensajeService service;

    @Test
    void listarMensajes() throws Exception {

        List<mensajeModel> mensajes = List.of(
                new mensajeModel(
                        1,
                        "Compra realizada",
                        "Compra",
                        "Su compra fue procesada correctamente",
                        LocalDateTime.now()
                )
        );

        when(service.obtenerTodosLosMensajes())
                .thenReturn(mensajes);

        mockMvc.perform(get("/api/mensajes/listar"))
                .andExpect(status().isOk());
    }
    
    @Test
    void crearMensaje() throws Exception {

        String mensajeJson = """
        {
            "nombre":"Compra realizada",
            "categoria":"Compra",
            "contenido":"Su compra fue procesada correctamente"
        }
        """;

        mensajeModel mensaje = new mensajeModel(
                1,
                "Compra realizada",
                "Compra",
                "Su compra fue procesada correctamente",
                LocalDateTime.now()
        );

        when(service.guardarMensaje(any(mensajeDTO.class)))
                .thenReturn(mensaje);

        mockMvc.perform(post("/api/mensajes/crear")
                .contentType(APPLICATION_JSON)
                .content(mensajeJson))
                .andExpect(status().isOk());
    }

}