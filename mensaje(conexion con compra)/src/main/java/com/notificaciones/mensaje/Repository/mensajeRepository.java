package com.notificaciones.mensaje.Repository;

import com.notificaciones.mensaje.Model.mensajeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface mensajeRepository extends JpaRepository< mensajeModel, Integer> {

}
