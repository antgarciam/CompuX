package com.pagos.compra.repository;

import com.pagos.compra.model.Compra;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;;

@Repository

public interface CompraRepository extends JpaRepository<Compra,Integer> {

    Optional<Compra> findByMontoIgnoreCase(Integer monto);

    List<Compra> findByMetodoPagoIgnoreCase(String metodoPago);
    List<Compra> findByEstadoPagoIgnoreCase(String estadoPago);

} 