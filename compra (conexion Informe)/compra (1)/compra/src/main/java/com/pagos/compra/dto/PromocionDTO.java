package com.pagos.compra.dto;

import lombok.Data;

@Data
public class PromocionDTO {
    private Integer productoId;

    private Double precioOriginal;

    private Double descuentoAplicado;

    private Double precioFinal;
    
    private String descripcion;
}
