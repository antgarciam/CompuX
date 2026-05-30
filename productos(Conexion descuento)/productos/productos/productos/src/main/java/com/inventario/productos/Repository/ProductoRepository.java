package com.inventario.productos.Repository;

import java.util.List;
import java.util.Optional;
import com.inventario.productos.Model.Producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;;

@Repository

public interface ProductoRepository extends JpaRepository<Producto,Integer> {

    List<Producto> findByStockIgnoreCase(Integer Stock);

    Optional<Producto> findByPrecioIgnoreCase(Integer Precio);
    List<Producto> findByCategoriaIgnoreCase(String categoria);

} 