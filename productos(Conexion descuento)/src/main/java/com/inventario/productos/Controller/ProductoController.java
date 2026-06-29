package com.inventario.productos.Controller;

import com.inventario.productos.Service.ProductoService;
import com.inventario.productos.dto.ProductoListadoDTO;
import com.inventario.productos.dto.ProductoPromocionDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

        @Operation(
        summary = "Listar productos",
        description = "Obtiene una lista con todas las productos registradas en el sistema"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})

    @GetMapping("/listar")
    public List<Producto> listar(){
        return service.listar();
    }

        @Operation(
        summary = "Mostrar productos",
        description = "Obtiene un catalogo con todos los productos registrados en el sistema"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})

    @GetMapping("/catalogo")
    public List<ProductoListadoDTO> listarDTO(){
        return service.listarDTO();
}

    @Operation(
        summary = "Buscar ID productos",
        description = "Obtiene el producto si la ID es correcta"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})


    @GetMapping("buscar/{id}")
    public Optional<Producto> buscarPorId(@PathVariable Integer id){
        return service.buscarPorId(id);
    }

    @Operation(
        summary = "Listar Categoria",
        description = "Muestra todas Las categorias"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})



    @GetMapping("categoria/{categoria}")
    public List<Producto> buscarPorCategoria(@PathVariable String categoria){
        return service.buscarPorCategoria(categoria);
    }

        @Operation(
        summary = "Agregar Producto",
        description = "Agrega un producto"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})

    @PostMapping("/agregar")
    public ResponseEntity<Producto> guardar(@Valid @RequestBody Producto producto){

    Producto nueva = service.guardar(producto);

    return ResponseEntity.status(201).body(nueva);
}   

    @Operation(
        summary = "Borrar Producto",
        description = "Borra el producto por su id"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})

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

        @Operation(
        summary = "Actualizar Producto",
        description = "Actualiza el producto por su ID"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})

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

    @Operation(
        summary = "Promocion",
        description = "Ver si un producto tiene ofertas buscandolo por su id"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})

    @GetMapping("producto-promocion/{id}")
    public ProductoPromocionDTO obtenerProductoPromocion(@PathVariable Integer id) {
        return service.obtenerProductoConPromocion(id);
    }

        @Operation(
        summary = "Precio Producto",
        description = "Ver el precio de un producto especificado por su id"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})

    @GetMapping("/{id}/precio")
    public Double obtenerPrecioProducto(@PathVariable Integer id) {
        Optional<Producto> producto = service.buscarPorId(id);
        
        if (producto.isPresent()) {
            
            return producto.get().getPrecio().doubleValue(); 
        } else {
            return 0.0; 
        }
    }

        @Operation(
        summary = "Actualizar Stock",
        description = "Modificar el stock de un producto"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})

    @PostMapping("/descontar")
    public String descontarStock(@RequestBody com.inventario.productos.dto.ActualizarStockDTO dto){
        service.restarStock((dto.getProductoId()), dto.getCantidad());

        return "Stock modificado con exito";
    }
}
    
