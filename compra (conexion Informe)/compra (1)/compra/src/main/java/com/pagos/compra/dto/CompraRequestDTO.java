package com.pagos.compra.dto;

import lombok.Data;

@Data
public class CompraRequestDTO {

    private Integer usuarioId;

    private Integer productoId;

    private Integer cantidad;
}
