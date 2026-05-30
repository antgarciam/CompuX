package com.inventario.productos;

import com.inventario.productos.dto.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

//Esta clase captura errores de toda la aplicacion en forma global
//Evita tener try-catch en cada controller

@RestControllerAdvice
public class ManejadorErrores {

    @ExceptionHandler(MethodArgumentNotValidException.class)

    public ResponseEntity<ErrorDTO> manejarErroresValidacion(
        MethodArgumentNotValidException ex,
        HttpServletRequest request){
            Map<String, String> errores = new HashMap<>();

            ex.getBindingResult().getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });

            ErrorDTO errorDTO = new ErrorDTO(
                LocalDateTime.now(),
                400,  // Status y mensaje no se escriben en el codigo, se escriben solos, solamente hay que ingresar valores
                "Error de validación",
                errores,
                request.getRequestURI()
            );

            return ResponseEntity.badRequest().body(errorDTO);
        }

}