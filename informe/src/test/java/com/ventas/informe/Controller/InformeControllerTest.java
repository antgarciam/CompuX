package com.ventas.informe.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.ventas.informe.controller.VentaControlador;
import com.ventas.informe.model.Venta;
import com.ventas.informe.service.VentaServicio;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.mockito.ArgumentMatchers.any;


@WebMvcTest(VentaControlador.class)
public class InformeControllerTest {

    @Autowired
    private MockMvc mockMvc; 

    @MockitoBean 
    private VentaServicio service;

    @Test
    void crearVenta() throws Exception {

        String ventaJson = """
        {
            "codProducto":1,
            "cantidad":2,
            "precioTotal":460800
        }
        """;

        Venta venta = new Venta();

        when(service.guardarVenta(any(Venta.class)))
                .thenReturn(venta);

        mockMvc.perform(post("/ventas/agregar")
                .contentType(APPLICATION_JSON)
                .content(ventaJson))
                .andExpect(status().isCreated());
    }

    @Test
    void obtenerVentas() throws Exception {
    
        List<Venta> ventas = List.of(
                new Venta()
        );
    
        when(service.obtenerVentas(1))
                .thenReturn(ventas);
    
        mockMvc.perform(get("/ventas/1/ventas"))
                .andExpect(status().isOk());
    }
}