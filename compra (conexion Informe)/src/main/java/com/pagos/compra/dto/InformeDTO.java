package com.pagos.compra.dto;
import lombok.Data;

@Data
public class InformeDTO {

    private Integer Id;
    private String nombreProducto;
    private Integer cantidad;
    private Integer valor;

}
