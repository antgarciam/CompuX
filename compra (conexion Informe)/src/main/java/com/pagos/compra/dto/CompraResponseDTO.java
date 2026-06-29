package com.pagos.compra.dto;

import lombok.Data;

@Data
public class CompraResponseDTO {

    private Integer usuarioId;

    private Integer productoId;

    private Integer cantidad;

    private Double precioOriginal;

    private Double descuentoAplicado;

    private Double precioFinal;

    private String descripcionPromocion;

    private String estadoPago;
}
