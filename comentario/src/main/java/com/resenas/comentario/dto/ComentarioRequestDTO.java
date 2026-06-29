package com.resenas.comentario.dto;

import lombok.Data;

@Data
public class ComentarioRequestDTO {

    private Integer usuarioId;

    private Integer productoId;

    private String comentario;

    private Integer calificacion;

}
