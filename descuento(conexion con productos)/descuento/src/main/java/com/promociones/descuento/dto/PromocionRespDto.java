package com.promociones.descuento.dto;

import lombok.Data;

@Data
public class PromocionRespDto {
    private Integer productoId;
    private String descripcion;
    private Double precioOriginal;
    private Double descuentoAplicado;
    private Double precioFinal;
}
