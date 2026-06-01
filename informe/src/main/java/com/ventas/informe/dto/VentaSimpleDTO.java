package com.ventas.informe.dto;

import java.util.List;
import lombok.Data;

@Data
public class VentaSimpleDTO {

    private String nombreProducto;
    private Integer cantidad;
    private Integer valor;
    private Integer codProducto;

    private List<String> Ventas;

}
