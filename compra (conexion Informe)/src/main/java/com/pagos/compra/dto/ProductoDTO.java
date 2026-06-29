package com.pagos.compra.dto;

import lombok.Data;

@Data
public class ProductoDTO {

    private Integer productoId;

    private String NombreProducto;

    private Integer Stock;

    private Integer Precio;

    private String categoria;
}
