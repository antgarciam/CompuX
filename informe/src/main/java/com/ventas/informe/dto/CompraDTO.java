package com.ventas.informe.dto;

import lombok.Data;
@Data
public class CompraDTO {

     private Integer id;

    private Integer usuarioId;

    private Integer productoId;

    private Integer cantidad;

    private Integer monto;

    private String metodoPago;

    private String estadoPago;

}
