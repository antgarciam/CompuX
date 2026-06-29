package com.notificaciones.mensaje.Controller;

import com.notificaciones.mensaje.Model.*;
import com.notificaciones.mensaje.Service.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.notificaciones.mensaje.DTO.mensajeDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/mensajes")
@CrossOrigin(origins = "*")

public class mensajeController {

    @Autowired
    private mensajeService service;
    
            @Operation(
        summary = "Crear Mensaje",
        description = "Crea una notificacion"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})
        
        @PostMapping("/crear")
        public ResponseEntity <mensajeModel> crearMensaje(@Valid@RequestBody mensajeDTO DTO){
            mensajeModel guardado = service.guardarMensaje(DTO);
            return ResponseEntity.ok(guardado);
        }

            @Operation(
        summary = "Listar Mensajes",
        description = "Listar Todas las notificaciones"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})

        @GetMapping("/listar")
        public ResponseEntity <List<mensajeModel>> listarMensajes(){
            List<mensajeModel> Lista = service.obtenerTodosLosMensajes();   
            return ResponseEntity.ok(Lista);
        }
    }



