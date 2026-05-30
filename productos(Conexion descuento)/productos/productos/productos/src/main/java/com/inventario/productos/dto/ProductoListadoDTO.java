package com.inventario.productos.dto;


//DTO para devolver datos de producto segun lo que se requiera mostrar 
//No es una entidad,no se guarda en la base de datos

import lombok.Data;

@Data // crea @Getter, @Setter etc

public class ProductoListadoDTO {

    private String NombreProducto;
    private Integer Stock;
    private Integer Precio;
    private String Categoria;
}

