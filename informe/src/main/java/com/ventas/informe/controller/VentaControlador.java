package com.ventas.informe.controller;

import com.ventas.informe.dto.VentaInformeDTO;
import com.ventas.informe.model.Venta;
import com.ventas.informe.service.VentaServicio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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

        @Operation(
        summary = "Agregar informe",
        description = "Crea Un informe"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})

    @PostMapping("/agregar")
    public ResponseEntity<Venta> crearVenta(@Valid @RequestBody Venta venta){

        Venta nueva = service.guardarVenta(venta);
            return ResponseEntity.status(201).body(nueva);
    }

        @Operation(
        summary = "Informe De Producto",
        description = "Ver el informe de un producto por su ID"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})

    @GetMapping("/{codProducto}/ventas")
    public List<Venta> obtenerVentas(@PathVariable Integer codProducto){
        return service.obtenerVentas(codProducto);
    }

    @Operation(
        summary = "Informe de compra",
        description = "Ver el informe de una compra"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})

     @GetMapping("/{compraId}")
    public VentaInformeDTO generarInforme(
            @PathVariable Integer compraId) {

        return service.generarInforme(compraId);
    }
}



