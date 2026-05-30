package com.resenas.comentario.controller;
import com.resenas.comentario.model.Comentario;
import com.resenas.comentario.service.ComentarioServicio;
import com.resenas.comentario.dto.ComentarioListadoDTO;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController

@RequestMapping("/resenias")
public class ComentarioControlador {
@Autowired
    private ComentarioServicio service;
    

    @GetMapping("/listar")
    public List<Comentario> listar(){
        return service.listar();
    }

    @GetMapping("/listar-dto")
    public List<ComentarioListadoDTO> listarDTO(){
        return service.listarDTO();
}

    
    @GetMapping("buscar/{id}")
    public Optional<Comentario> buscarPorId(@PathVariable Integer id){
        return service.buscarPorId(id);
    }

    @PostMapping("/agregar")
    public ResponseEntity<Comentario> guardar(@Valid @RequestBody Comentario comentario){

    
    Comentario nueva = service.guardar(comentario);

    
    return ResponseEntity.status(201).body(nueva);
}   

    @DeleteMapping("eliminar/{id}")
    public String eliminar(@PathVariable Integer id){
        Optional<Comentario> comentario = service.buscarPorId(id);
        if(comentario.isPresent()){   
            service.eliminarPorId(id);
            return "El comentario fue eliminado con éxito";
        }else {
            return "comentario no encontrada con id: " + id;
        }
    }

    @PutMapping("actualizar/{id}")
    public String actualizar(@PathVariable Integer id, @RequestBody Comentario comentario) {
        
        Optional<Comentario> existente = service.buscarPorId(id);
        if(existente.isPresent()){
            service.actualizarComentario(id, comentario);
            return "comentario Actualizado correctamente";
        }else {
            return "comentario no encontrado con id: "+id;
        }
        
    }
}
