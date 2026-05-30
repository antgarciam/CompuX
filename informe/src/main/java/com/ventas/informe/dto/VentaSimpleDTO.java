package com.ventas.informe.dto;

import com.ventas.informe.model.Venta;
import lombok.Data;


// DTO simplificado
// Solo muestra lo que esperamos responder

@Data

public class VentaSimpleDTO {

    private String nombreProducto;
    private Integer Id;


}
