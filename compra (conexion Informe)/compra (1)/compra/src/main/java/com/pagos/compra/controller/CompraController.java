package com.pagos.compra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pagos.compra.dto.CompraListadoDTO;
import com.pagos.compra.dto.CompraRequestDTO;
import com.pagos.compra.dto.CompraResponseDTO;
import com.pagos.compra.model.Compra;
import com.pagos.compra.service.CompraService;

import java.util.List;
import java.util.Optional;


@RestController

@RequestMapping("/pagos")
public class CompraController {
    @Autowired
    private CompraService service;
  

    @GetMapping("/listar")
    public List<Compra> listar(){
        return service.listar();
    }

    @GetMapping("/listar-dto")
    public List<CompraListadoDTO> listarDTO(){
        return service.listarDTO();
}

  
    @GetMapping("buscar/{id}")
    public Optional<Compra> buscarPorId(@PathVariable Integer id){
        return service.buscarPorId(id);
    }

    @GetMapping("metodo/{metodoPago}")
    public List<Compra> buscarPorMetodoPago(@PathVariable String metodoPago){
        return service.buscarPorMetodoPago(metodoPago);
    }

    @GetMapping("estado/{estadoPago}")
    public List<Compra> buscarPorEstadoPago(@PathVariable String estadoPago){
        return service.buscarPorEstadoPago(estadoPago);
    }


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


    @PostMapping("/agregar")
    public ResponseEntity<CompraResponseDTO> comprar( @RequestBody CompraRequestDTO dto){

    return ResponseEntity.ok(
            service.realizarCompra(dto)
        );
    }
    
}
