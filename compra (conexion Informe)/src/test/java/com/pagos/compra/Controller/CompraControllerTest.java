package com.pagos.compra.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.pagos.compra.controller.CompraController;
import com.pagos.compra.dto.CompraRequestDTO;
import com.pagos.compra.dto.CompraResponseDTO;
import com.pagos.compra.model.Compra;
import com.pagos.compra.service.CompraService;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.mockito.ArgumentMatchers.any;


@WebMvcTest(CompraController.class)
public class CompraControllerTest {

    @Autowired
    private MockMvc mockMvc; 

    @MockitoBean 
    private CompraService service;

    @Test
    void listarCompras() throws Exception {

        List<Compra> compras = List.of(new Compra());

        when(service.listar()).thenReturn(compras);

        mockMvc.perform(get("/pagos/listar"))
                .andExpect(status().isOk());
    }

    @Test
    void buscarCompraPorId() throws Exception {

        Compra compra = new Compra();

        when(service.buscarPorId(1))
                .thenReturn(Optional.of(compra));

        mockMvc.perform(get("/pagos/buscar/1"))
                .andExpect(status().isOk());
    }

    @Test
    void buscarPorMetodoPago() throws Exception {

        List<Compra> compras = List.of(new Compra());

        when(service.buscarPorMetodoPago("Tarjeta"))
                .thenReturn(compras);

        mockMvc.perform(get("/pagos/metodo/Tarjeta"))
                .andExpect(status().isOk());
    }
    @Test
    void buscarPorEstadoPago() throws Exception {

    List<Compra> compras = List.of(new Compra());

    when(service.buscarPorEstadoPago("PAGADO"))
            .thenReturn(compras);

    mockMvc.perform(get("/pagos/estado/PAGADO"))
            .andExpect(status().isOk());
    }

    @Test
    void realizarCompra() throws Exception {

        String compraJson = """
        {
            "usuarioId":1,
            "productoId":1,
            "cantidad":2
        }
        """;

        when(service.realizarCompra(any(CompraRequestDTO.class)))
                .thenReturn(new CompraResponseDTO());

        mockMvc.perform(post("/pagos/agregar")
                .contentType(APPLICATION_JSON)
                .content(compraJson))
                .andExpect(status().isOk());
    }
}
