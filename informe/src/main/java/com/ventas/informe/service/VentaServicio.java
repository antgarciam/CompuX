package com.ventas.informe.service;

import com.ventas.informe.dto.CompraDTO;
import com.ventas.informe.dto.ProductoDTO;
import com.ventas.informe.dto.UsuarioDTO;
import com.ventas.informe.dto.VentaInformeDTO;
import com.ventas.informe.model.Venta;
import com.ventas.informe.repository.VentaRepositorio;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VentaServicio {

    private final VentaRepositorio repository;
    private final RestTemplate restTemplate = new RestTemplate();

    public VentaServicio(VentaRepositorio repository) {
        this.repository = repository;
    }

    public VentaInformeDTO generarInforme(Integer compraId) {

    CompraDTO compra =
            restTemplate.getForObject(
                    "http://localhost:8085/pagos/buscar/" + compraId,
                    CompraDTO.class
            );

    if (compra == null) {
        throw new RuntimeException("Compra no encontrada");
    }

    UsuarioDTO usuario =
            restTemplate.getForObject(
                    "http://localhost:8080/usuario/buscar/" + compra.getUsuarioId(),
                    UsuarioDTO.class
            );

    ProductoDTO producto =
            restTemplate.getForObject(
                    "http://localhost:8081/productos/buscar/" + compra.getProductoId(),
                    ProductoDTO.class
            );

    VentaInformeDTO  informe = new VentaInformeDTO ();

    informe.setCompraId(compra.getId());
    informe.setNombreUsuario(usuario.getNombreUsuario());
    informe.setNombreProducto(producto.getNombreProducto());
    informe.setCantidad(compra.getCantidad());
    informe.setMonto(compra.getMonto());
    informe.setMetodoPago(compra.getMetodoPago());
    informe.setEstadoPago(compra.getEstadoPago());

    return informe;
}

    public Venta guardarVenta(Venta venta) {
        return repository.save(venta);
    }

    public List<Venta> obtenerVentas(Integer codProducto) {

        Optional<Venta> ventaOpt = repository.findById(codProducto);

        if (ventaOpt.isEmpty()) {
            return new ArrayList<>();
        }

        List<Venta> ventas = new ArrayList<>();
        ventas.add(ventaOpt.get());

        return ventas;
    }
}