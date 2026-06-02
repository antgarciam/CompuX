package com.resenas.comentario.service;
import com.resenas.comentario.dto.ComentarioRequestDTO;
import com.resenas.comentario.dto.ComentarioResponseDTO;
import com.resenas.comentario.dto.ProductoDTO;
import com.resenas.comentario.dto.UsuarioDTO;
import  com.resenas.comentario.model.Comentario;
import com.resenas.comentario.repository.ComentarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;


@Service
public class ComentarioServicio {
@Autowired 
    private ComentarioRepositorio repository;

    private final RestTemplate restTemplate = new RestTemplate();
    
    public ComentarioResponseDTO crearComentario(
        ComentarioRequestDTO dto) {

    UsuarioDTO usuario =
            restTemplate.getForObject(
                    "http://localhost:8080/usuario/buscar/"
                            + dto.getUsuarioId(),
                    UsuarioDTO.class
            );

    ProductoDTO producto =
            restTemplate.getForObject(
                    "http://localhost:8081/productos/buscar/"
                            + dto.getProductoId(),
                    ProductoDTO.class
            );

    if(usuario == null){
        throw new RuntimeException(
                "Usuario no encontrado"
        );
    }

    if(producto == null){
        throw new RuntimeException(
                "Producto no encontrado"
        );
    }

    Comentario comentario = new Comentario();

    comentario.setUsuarioId(
            dto.getUsuarioId()
    );

    comentario.setProductoId(
            dto.getProductoId()
    );

    comentario.setComentario(
            dto.getComentario()
    );

    comentario.setCalificacion(
            dto.getCalificacion()
    );

    repository.save(comentario);

    ComentarioResponseDTO respuesta = new ComentarioResponseDTO();

    respuesta.setId(
            comentario.getId()
    );

    respuesta.setUsuarioId(
            usuario.getId()
    );

    respuesta.setNombreUsuario(
            usuario.getNombreUsuario()
    );

    respuesta.setProductoId(
            producto.getId()
    );

    respuesta.setNombreProducto(
            producto.getNombreProducto()
    );

    respuesta.setComentario(
            comentario.getComentario()
    );

    respuesta.setCalificacion(
            comentario.getCalificacion()
    );

    return respuesta;
}

    public List<Comentario> listar(){
        return repository.findAll();
    }
    
    public Optional<Comentario> buscarPorId(Integer id){
        return repository.findById(id);
    }

}




