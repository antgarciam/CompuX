package com.resenas.comentario.controller;
import com.resenas.comentario.dto.ComentarioRequestDTO;
import com.resenas.comentario.dto.ComentarioResponseDTO;
import com.resenas.comentario.model.Comentario;
import com.resenas.comentario.service.ComentarioServicio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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

    @Operation(
        summary = "Listar Comentarios",
        description = "Muestra todos los comentarios"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})

    @GetMapping("/listar")
    public List<Comentario> listar(){
        return service.listar();
    }

    @Operation(
        summary = "Buscar por id",
        description = "Buscar comentario Por su id"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})    

    @GetMapping("buscar/{id}")
    public Optional<Comentario> buscarPorId(@PathVariable Integer id){
        return service.buscarPorId(id);
    }

    @Operation(
        summary = "Agregar comentario",
        description = "Agrega un nuevo comentario"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})

    @PostMapping("/agregar")
    public ResponseEntity<ComentarioResponseDTO> agregarComentario(@RequestBody ComentarioRequestDTO dto){
        return ResponseEntity.ok(service.crearComentario(dto));
    }


   
}
