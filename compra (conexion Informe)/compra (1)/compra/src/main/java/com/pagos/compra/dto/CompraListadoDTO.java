package com.pagos.compra.dto;


import lombok.Data;

@Data

public class CompraListadoDTO {

    private Integer monto;
    private String metodoPago;
    private String estadoPago;
}