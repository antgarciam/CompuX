package com.ventas.informe.dto;

import lombok.Data;

@Data
public class ProductoDTO {

    private Integer id;

    private String NombreProducto;

    private Integer Stock;

    private Integer Precio;

    private String categoria;

}