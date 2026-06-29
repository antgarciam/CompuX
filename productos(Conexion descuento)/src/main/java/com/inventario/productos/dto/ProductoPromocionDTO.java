package com.inventario.productos.dto;

import lombok.Data;

@Data
public class ProductoPromocionDTO {
    private Integer id;

    private String NombreProducto;

    private Integer Precio;

    private Double valor;

    private double precioFinal;

}
