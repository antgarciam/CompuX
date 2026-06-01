package com.ventas.informe.repository;

import com.ventas.informe.model.Venta;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface VentaRepositorio extends JpaRepository <Venta, Integer>{

    Optional<Venta> findByNombreCodProductoIgnoreCase(Integer codProducto);
    
    Optional<Venta> findByNombreProducto(String nombreProducto);
}


