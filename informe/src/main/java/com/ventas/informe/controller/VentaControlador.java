package com.ventas.informe.controller;

import com.ventas.informe.service.VentaServicio;
import com.ventas.informe.model.Venta;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/peliculas")
public class VentaControlador {
@Autowired
    private VentaServicio service;
    // Se inyecta el servicio para acceder a la logica de negocio


    @GetMapping("/venta/{producto}")
    public Optional <Venta> buscarPorNombreProducto(@PathVariable String nombreProducto){
        return service.buscarPorNombreProducto(nombreProducto);
    }

    //Get; Buscar por ID
    @GetMapping("/venta/{id}")
    public Optional<Venta> buscarPorId(@PathVariable Integer id){
        return service.buscarPorId(id);
    }

    @PostMapping
    public Venta crear(@RequestBody Venta venta) {
        return service.guardarVenta(venta);
    }

    @DeleteMapping("Eliminar/{id}")
    public String eliminar(@PathVariable Integer id){
        Optional<Venta> venta = service.buscarPorId(id);
        if(venta.isPresent()){
            service.eliminarPorId(id);
            return "Informe eliminado";
        }else {
            return "Informe no encontrado con id: " + id;
        }
    }

    @PutMapping("actualizar/{id}")
    public String actualizar(@PathVariable Integer id, @RequestBody Venta venta) {
        //TODO: process PUT request
        Optional<Venta> existente = service.buscarPorId(id);
        if(existente.isPresent()){
            service.actualizarVenta(id, venta);
            return "Informe Actualizado correctamente";
        }else {
            return "Informec  no encontrado con id: "+id;
        }
        
    }
}
