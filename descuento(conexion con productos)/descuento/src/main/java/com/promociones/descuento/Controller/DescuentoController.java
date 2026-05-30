package com.promociones.descuento.Controller;

import com.promociones.descuento.Model.DescuentoModel;
import com.promociones.descuento.Service.DescuentoService;
import com.promociones.descuento.dto.PromocionReqDTO;
import com.promociones.descuento.dto.PromocionRespDto;
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

    @PostMapping("/agregar")
    public ResponseEntity<DescuentoModel> crearDescuento(@Valid @RequestBody DescuentoModel descuento) {
        DescuentoModel nuevo = service.guardarDescuento(descuento);
        
        return ResponseEntity.status(201).body(nuevo);
    }

    @GetMapping("/listar-dto")
    public List<PromocionReqDTO> listarDTO() {
        return service.listarDTO();
    }

    @GetMapping("/{productoId}/detalle-descuento")
    public PromocionRespDto obtenerDetalleDescuento(@PathVariable Integer productoId) {
        return service.obtenerDetalleDescuento(productoId);
    }
}