package com.promociones.descuento.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.promociones.descuento.Model.DescuentoModel;
import com.promociones.descuento.Service.DescuentoService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.mockito.ArgumentMatchers.any;


@WebMvcTest(DescuentoController.class)
public class DescuentoControllerTest {

    @Autowired
    private MockMvc mockMvc; 

    @MockitoBean 
    private DescuentoService service;

    @Test
    void crearDescuento() throws Exception {

        String descuentoJson = """
        {
            "productoId":1,
            "descripcion":"20% de descuento en GPU",
            "tipoDescuento":"PORCENTAJE",
            "valor":20.0,
            "fechaInicio":"2026-06-17T00:00:00",
            "fechaFin":"2026-06-30T23:59:59",
            "activo":true
        }
        """;

        DescuentoModel descuento = new DescuentoModel(
                1,
                1,
                "20% de descuento en GPU",
                "PORCENTAJE",
                20.0,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(10),
                true
        );

        when(service.guardarDescuento(any(DescuentoModel.class)))
                .thenReturn(descuento);

        mockMvc.perform(post("/descuentos/agregar")
                .contentType(APPLICATION_JSON)
                .content(descuentoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void listarDTO() throws Exception {
    
        when(service.listarDTO())
                .thenReturn(List.of());
    
        mockMvc.perform(get("/descuentos/listar-dto"))
                .andExpect(status().isOk());
    }
}