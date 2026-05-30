package com.cliente.usuario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import com.cliente.usuario.dto.UsuarioListadoDTO;
import com.cliente.usuario.model.Usuario;
import com.cliente.usuario.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> listar(){
        return repository.findAll();
    }

    public Optional<Usuario> buscarPorId(Integer id){
        return repository.findById(id);
    }
    
    public Usuario guardar(Usuario usuario) {
        return repository.save(usuario);
    }

    public void eliminarPorId(Integer id){
        repository.deleteById(id);
    }

    public Usuario actualizarUsuario(Integer id, Usuario usuario){
        usuario.setId(id);
        return repository.save(usuario);
    }
    
    public List<Usuario> buscarPorNombreUsuario(String nombreUsuario){
        return repository.findByNombreUsuarioIgnoreCase(nombreUsuario);
    }

     public List<UsuarioListadoDTO> listarDTO(){
        List<Usuario> usuario = repository.findAll(); 
        List<UsuarioListadoDTO> lista = new ArrayList<>();

        for(Usuario p : usuario){   
            UsuarioListadoDTO dto = new UsuarioListadoDTO(); 
            dto.setNombreUsuario(p.getNombreUsuario());
            dto.setCorreoUsuario(p.getCorreoUsuario());

            lista.add(dto); 
        }
        return lista; 
    }

}
