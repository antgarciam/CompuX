package com.pagos.compra.dto;

public class ActualizarStockDTO {

    private Integer productoId;
    private Integer cantidad;

    
    
    public ActualizarStockDTO() {
    }

    public ActualizarStockDTO(Integer productoId, Integer cantidad) {
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    

}
