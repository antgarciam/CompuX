package com.ventas.informe.repository;

import com.ventas.informe.model.Venta;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;;

@Repository

public interface VentaRepositorio extends JpaRepository <Venta, Integer>{

    Optional<Venta> findByNombreProductoIgnoreCase(String nombreProducto);
    
    Optional<Venta> findByIdIgnoreCase(Integer Id);
}


