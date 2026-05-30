package com.pagos.compra.dto;

import lombok.Data;

@Data

public class CompraInformeDTO {

    private Integer id;

    private String metodoPago;

    private String estadoPago;

    private Integer monto;

    private Integer idProducto;
    
    private Integer cantidad;


}
