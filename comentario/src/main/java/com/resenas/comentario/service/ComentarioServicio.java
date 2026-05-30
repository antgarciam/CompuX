package com.resenas.comentario.service;
import  com.resenas.comentario.model.Comentario;
import com.resenas.comentario.repository.ComentarioRepositorio;
import com.resenas.comentario.dto.ComentarioListadoDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ComentarioServicio {
@Autowired 
    private ComentarioRepositorio repository;

    
    public List<Comentario> listar(){
        return repository.findAll();
    }

    
    public Optional<Comentario>buscarPorTitulo(String titulo){
        return repository.findByTituloIgnoreCase(titulo);
    }

    
    public Optional<Comentario> buscarPorId(Integer id){
        return repository.findById(id);
    }



    public Comentario guardar(Comentario comentario) {
        return repository.save(comentario);
    }

    public void eliminarPorId(Integer Id){
        repository.deleteById(Id);
    }

    public Comentario actualizarComentario(Integer id, Comentario comentario){
        comentario.setId(id);
        return repository.save(comentario);
    }

    public List<ComentarioListadoDTO> listarDTO(){
        List<Comentario> comentario = repository.findAll(); 
        List<ComentarioListadoDTO> lista = new ArrayList<>(); 

        for(Comentario p : comentario){   
            ComentarioListadoDTO dto = new ComentarioListadoDTO(); 
            dto.setTitulo(p.getTitulo());


            lista.add(dto); 
        }
        return lista; 
    }
}
