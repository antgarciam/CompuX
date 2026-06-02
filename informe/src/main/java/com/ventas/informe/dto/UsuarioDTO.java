package com.ventas.informe.dto;
import lombok.Data;

@Data
public class UsuarioDTO {

    private Integer id;

    private String nombreUsuario;

    private String apellidoUsuario;

    private String correoUsuario;
}