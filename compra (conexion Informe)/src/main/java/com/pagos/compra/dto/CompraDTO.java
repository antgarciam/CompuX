package com.pagos.compra.dto;

import lombok.Data;

@Data

public class CompraDTO {
    private Integer compraId;

    private Integer usuarioId;

    private Integer productoId;

    private Integer cantidad;

    private Integer monto;

    private String metodoPago;

    private String estadoPago;
}
