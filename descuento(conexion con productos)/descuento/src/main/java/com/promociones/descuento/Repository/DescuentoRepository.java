package com.promociones.descuento.Repository;

import com.promociones.descuento.Model.DescuentoModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DescuentoRepository extends JpaRepository<DescuentoModel, Integer> {
    
    List<DescuentoModel> findByProductoId(Integer productoId);
}