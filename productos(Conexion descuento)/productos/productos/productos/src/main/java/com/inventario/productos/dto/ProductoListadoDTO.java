package com.inventario.productos.dto;



import lombok.Data;

@Data 

public class ProductoListadoDTO {

    private String NombreProducto;
    private Integer Stock;
    private Integer Precio;
    private String Categoria;
}

