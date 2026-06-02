package com.resenas.comentario.controller;
import com.resenas.comentario.dto.ComentarioRequestDTO;
import com.resenas.comentario.dto.ComentarioResponseDTO;
import com.resenas.comentario.model.Comentario;
import com.resenas.comentario.service.ComentarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController

@RequestMapping("/resenas")
public class ComentarioControlador {
@Autowired
    private ComentarioServicio service;
    

    @GetMapping("/listar")
    public List<Comentario> listar(){
        return service.listar();
    }
    
    @GetMapping("buscar/{id}")
    public Optional<Comentario> buscarPorId(@PathVariable Integer id){
        return service.buscarPorId(id);
    }

    @PostMapping("/agregar")
    public ResponseEntity<ComentarioResponseDTO> agregarComentario(@RequestBody ComentarioRequestDTO dto){
        return ResponseEntity.ok(service.crearComentario(dto));
    }


   
}
