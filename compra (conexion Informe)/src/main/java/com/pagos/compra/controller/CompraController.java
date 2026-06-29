package com.pagos.compra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pagos.compra.dto.CompraListadoDTO;
import com.pagos.compra.dto.CompraRequestDTO;
import com.pagos.compra.dto.CompraResponseDTO;
import com.pagos.compra.model.Compra;
import com.pagos.compra.service.CompraService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.Optional;


@RestController

@RequestMapping("/pagos")
public class CompraController {
    @Autowired
    private CompraService service;
  
    @Operation(
        summary = "Listar Compras",
        description = "Mostrar todas las compras"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})
    
    @GetMapping("/listar")
    public List<Compra> listar(){
        return service.listar();
    }

    @Operation(
        summary = "Listar DTO",
        description = "Muestra Todos los DTOs"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})

    @GetMapping("/listar-dto")
    public List<CompraListadoDTO> listarDTO(){
        return service.listarDTO();
}

        @Operation(
        summary = "Buscar por id",
        description = "Busca un producto por su id"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})
  
    @GetMapping("buscar/{id}")
    public Optional<Compra> buscarPorId(@PathVariable Integer id){
        return service.buscarPorId(id);
    }

    @Operation(
        summary = "Metodo de pago",
        description = "Ver el metodo de pago"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})

    @GetMapping("metodo/{metodoPago}")
    public List<Compra> buscarPorMetodoPago(@PathVariable String metodoPago){
        return service.buscarPorMetodoPago(metodoPago);
    }

        @Operation(
        summary = "Estado de pago",
        description = "Ver el estado de pago"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})

    @GetMapping("estado/{estadoPago}")
    public List<Compra> buscarPorEstadoPago(@PathVariable String estadoPago){
        return service.buscarPorEstadoPago(estadoPago);
    }

        @Operation(
        summary = "Eliminar compra",
        description = "Borra la compra por su ID"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})

    @DeleteMapping("eliminar/{id}")
    public String eliminar(@PathVariable Integer id){
        Optional<Compra> compra = service.buscarPorId(id);
        if(compra.isPresent()){   
            service.eliminarPorId(id);
            return "El Pago fue eliminado con éxito";
        }else {
            return "El pago no fue encontrado con id: " + id;
        }
    }

        @Operation(
        summary = "Actualizar compra",
        description = "Actualiza la compra por su ID"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})

    @PutMapping("actualizar/{id}")
    public String actualizar(@PathVariable Integer id, @RequestBody Compra compra) {
        
        Optional<Compra> existente = service.buscarPorId(id);
        if(existente.isPresent()){
            service.actualizarCompra(id, compra);
            return "Pago Actualizado correctamente";
        }else {
            return "Pago no encontrado con id: "+id;
        }
        
    }

        @Operation(
        summary = "Agregar Compra",
        description = "Agrega una compra nueva"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})

    @PostMapping("/agregar")
    public ResponseEntity<CompraResponseDTO> comprar( @RequestBody CompraRequestDTO dto){

    return ResponseEntity.ok(
            service.realizarCompra(dto)
        );
    }
    
}
