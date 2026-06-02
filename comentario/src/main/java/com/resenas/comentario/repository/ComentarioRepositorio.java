package com.resenas.comentario.repository;
import com.resenas.comentario.model.Comentario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ComentarioRepositorio extends JpaRepository<Comentario,Integer>{ 

}
