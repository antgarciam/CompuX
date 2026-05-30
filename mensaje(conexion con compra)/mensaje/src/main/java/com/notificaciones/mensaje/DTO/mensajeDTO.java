package com.notificaciones.mensaje.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class mensajeDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100)
    private String nombre;

    @NotBlank(message = "La categoría del mensaje no puede estar vacía")
    private String categoria;

    @NotBlank(message = "El contenido del mensaje no puede estar vacío")
    @Size(max = 300)
    private String contenido;

}
