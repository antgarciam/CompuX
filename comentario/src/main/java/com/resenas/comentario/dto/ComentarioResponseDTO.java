package com.resenas.comentario.dto;

import lombok.Data;

@Data

public class ComentarioResponseDTO {
    private Integer id;

    private Integer usuarioId;

    private String nombreUsuario;

    private Integer productoId;

    private String nombreProducto;

    private String comentario;

    private Integer calificacion;

}
