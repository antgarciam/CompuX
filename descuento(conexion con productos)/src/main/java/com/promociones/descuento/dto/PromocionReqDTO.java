package com.promociones.descuento.dto;

import lombok.Data;

@Data
public class PromocionReqDTO {
    
    private Integer productoId;
    private String descripcion;
    private String tipoDescuento;
    private Double valor;
}