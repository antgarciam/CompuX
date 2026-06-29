package com.cliente.usuario.dto;


import lombok.Data;

@Data 

public class ProductoListadoDTO {

    private String nombreProducto;
    private Integer stock;
    private Integer precio;
    private String categoria;
}


