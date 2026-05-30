package com.inventario.productos.Controller;

import com.inventario.productos.Service.ProductoService;
import com.inventario.productos.dto.ProductoListadoDTO;
import com.inventario.productos.dto.ProductoPromocionDTO;

import jakarta.validation.Valid;

import com.inventario.productos.Model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController

@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoService service;

    @GetMapping("/listar")
    public List<Producto> listar(){
        return service.listar();
    }

    @GetMapping("/listar-dto")
    public List<ProductoListadoDTO> listarDTO(){
        return service.listarDTO();
}

    @GetMapping("buscar/{id}")
    public Optional<Producto> buscarPorId(@PathVariable Integer id){
        return service.buscarPorId(id);
    }

    @GetMapping("categoria/{categoria}")
    public List<Producto> buscarPorCategoria(@PathVariable String categoria){
        return service.buscarPorCategoria(categoria);
    }

    @PostMapping("/agregar")
    public ResponseEntity<Producto> guardar(@Valid @RequestBody Producto producto){

    Producto nueva = service.guardar(producto);

    return ResponseEntity.status(201).body(nueva);
}   

    @DeleteMapping("eliminar/{id}")
    public String eliminar(@PathVariable Integer id){
        Optional<Producto> producto = service.buscarPorId(id);
        if(producto.isPresent()){   
            service.eliminarPorId(id);
            return "El producto fue eliminado con éxito";
        }else {
            return "producto no encontrada con id: " + id;
        }
    }

    @PutMapping("actualizar/{id}")
    public String actualizar(@PathVariable Integer id, @RequestBody Producto producto) {
        
        Optional<Producto> existente = service.buscarPorId(id);
        if(existente.isPresent()){
            service.actualizarProducto(id, producto);
            return "Producto Actualizado correctamente";
        }else {
            return "Producto no encontrado con id: "+id;
        }
        
    }

    @GetMapping("producto-promocion/{id}")
    public ProductoPromocionDTO obtenerProductoPromocion(@PathVariable Integer id) {
        return service.obtenerProductoConPromocion(id);
    }


    @GetMapping("/{id}/precio")
    public Double obtenerPrecioProducto(@PathVariable Integer id) {
        Optional<Producto> producto = service.buscarPorId(id);
        
        if (producto.isPresent()) {
            
            return producto.get().getPrecio().doubleValue(); 
        } else {
            return 0.0; 
        }
    }

    @PostMapping("/descontar")
    public String descontarStock(@RequestBody com.inventario.productos.dto.ActualizarStockDTO dto){
        service.restarStock((dto.getProductoId()), dto.getCantidad());

        return "Stock modificado con exito";
    }
}
    
