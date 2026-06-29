package com.cliente.usuario.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cliente.usuario.model.Usuario;;

@Repository

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    List<Usuario> findByNombreUsuarioIgnoreCase(String nombreUsuario);
    List<Usuario> findByApellidoUsuarioIgnoreCase(String apellidoUsuario);
} 