package com.ventas.informe.dto;

import lombok.Data;

@Data
public class VentaInformeDTO {

     private Integer compraId;

    private String nombreUsuario;

    private String nombreProducto;

    private Integer cantidad;

    private Integer monto;

    private String metodoPago;

    private String estadoPago;
}