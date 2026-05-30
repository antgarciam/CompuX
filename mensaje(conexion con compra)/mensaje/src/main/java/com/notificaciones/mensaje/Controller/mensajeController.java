package com.notificaciones.mensaje.Controller;

import com.notificaciones.mensaje.Model.*;
import com.notificaciones.mensaje.Service.*;
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
    
        @PostMapping
        public ResponseEntity <mensajeModel> crearMensaje(@Valid@RequestBody mensajeDTO DTO){
            mensajeModel guardado = service.guardarMensaje(DTO);
            return ResponseEntity.ok(guardado);
        }

        @GetMapping
        public ResponseEntity <List<mensajeModel>> listarMensajes(){
            List<mensajeModel> Lista = service.obtenerTodosLosMensajes();   
            return ResponseEntity.ok(Lista);
        }
    }



