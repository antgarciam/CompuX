package com.promociones.descuento.Controller;

import com.promociones.descuento.Model.DescuentoModel;
import com.promociones.descuento.Service.DescuentoService;
import com.promociones.descuento.dto.PromocionReqDTO;
import com.promociones.descuento.dto.PromocionRespDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/descuentos") 
public class DescuentoController {

    private final DescuentoService service;

    
    public DescuentoController(DescuentoService service) {
        this.service = service;
    }

    @Operation(
        summary = "Agregar Descuento",
        description = "Agrega un descuento"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})



    @PostMapping("/agregar")
    public ResponseEntity<DescuentoModel> crearDescuento(@Valid @RequestBody DescuentoModel descuento) {
        DescuentoModel nuevo = service.guardarDescuento(descuento);
        
        return ResponseEntity.status(201).body(nuevo);
    }

        @Operation(
        summary = "Listar dto",
        description = "Lista Todo los DTOs"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})

    @GetMapping("/listar-dto")
    public List<PromocionReqDTO> listarDTO() {
        return service.listarDTO();
    }

    @Operation(
        summary = "Descuento Detallado",
        description = "Muestra a detalle el descuento de un producto por su id"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})

    @GetMapping("/{productoId}/detalle-descuento")
    public PromocionRespDto obtenerDetalleDescuento(@PathVariable Integer productoId) {
        return service.obtenerDetalleDescuento(productoId);
    }
}