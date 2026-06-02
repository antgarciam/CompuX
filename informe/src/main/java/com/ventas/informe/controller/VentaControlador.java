package com.ventas.informe.controller;

import com.ventas.informe.dto.VentaInformeDTO;
import com.ventas.informe.model.Venta;
import com.ventas.informe.service.VentaServicio;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import java.util.List;


@RestController
@RequestMapping("/ventas")
public class VentaControlador {

    private final VentaServicio service;

    public VentaControlador(VentaServicio service){
            this.service = service;
    }

    @PostMapping("/agregar")
    public ResponseEntity<Venta> crearVenta(@Valid @RequestBody Venta venta){

        Venta nueva = service.guardarVenta(venta);
            return ResponseEntity.status(201).body(nueva);
    }

    @GetMapping("/{id}/ventas")
    public List<Venta> obtenerVentas(@PathVariable Integer codProducto){
        return service.obtenerVentas(codProducto);
    }

     @GetMapping("/{compraId}")
    public VentaInformeDTO generarInforme(
            @PathVariable Integer compraId) {

        return service.generarInforme(compraId);
    }
}



