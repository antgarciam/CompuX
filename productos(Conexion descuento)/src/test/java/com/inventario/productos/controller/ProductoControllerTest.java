package com.inventario.productos.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.inventario.productos.Controller.ProductoController;
import com.inventario.productos.Model.Producto;
import com.inventario.productos.Service.ProductoService;
import com.inventario.productos.dto.ProductoListadoDTO;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.mockito.ArgumentMatchers.any;


@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc; 

    @MockitoBean 
    private ProductoService service;

    @Test
    void listarCatalogo() throws Exception {

        List<ProductoListadoDTO> productos = List.of();

        when(service.listarDTO()).thenReturn(productos);

        mockMvc.perform(get("/productos/catalogo"))
                .andExpect(status().isOk());
    }

        @Test
    void crearProducto() throws Exception {

        String productoJson = """
                {
                    "NombreProducto":"GTX 2060",
                    "Stock":6,
                    "Precio":230400,
                    "categoria":"GPU"
                }
                """;

        Producto producto = new Producto(
                1,
                "GTX 2060",
                6,
                230400,
                "GPU"
        );

        when(service.guardar(any(Producto.class)))
                .thenReturn(producto);

        mockMvc.perform(post("/productos/agregar")
                .contentType(APPLICATION_JSON)
                .content(productoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void buscarProductoPorId() throws Exception {

        Producto producto = new Producto(
                1,
                "GTX 2060",
                6,
                230400,
                "GPU"
        );

        when(service.buscarPorId(1))
                .thenReturn(Optional.of(producto));

        mockMvc.perform(get("/productos/buscar/1"))
                .andExpect(status().isOk());
    }

    @Test
    void buscarPorCategoria() throws Exception {

        List<Producto> productos = List.of(
                new Producto(
                        1,
                        "GTX 2060",
                        6,
                        230400,
                        "GPU"
                )
        );

        when(service.buscarPorCategoria("GPU"))
                .thenReturn(productos);

        mockMvc.perform(get("/productos/categoria/GPU"))
                .andExpect(status().isOk());
    }
}