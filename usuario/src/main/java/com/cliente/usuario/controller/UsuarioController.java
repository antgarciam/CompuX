package com.cliente.usuario.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cliente.usuario.dto.UsuarioListadoDTO;
import com.cliente.usuario.model.Usuario;
import com.cliente.usuario.service.UsuarioService;

import java.util.List;
import java.util.Optional;


@RestController

@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @GetMapping("/listar")
    public List<Usuario> listar(){
        return service.listar();
    }

    @GetMapping("/listar-dto")
    public List<UsuarioListadoDTO> listarDTO(){
        return service.listarDTO();
}
    @GetMapping("buscar/{id}")
    public Optional<Usuario> buscarPorId(@PathVariable Integer id){
        return service.buscarPorId(id);
    }

    @GetMapping("nombre/{nombreUsuario}")
    public List<Usuario> buscarPorNombreUsuario(@PathVariable String nombreUsuario){
        return service.buscarPorNombreUsuario(nombreUsuario);
    }

    @PostMapping("/agregar")
    public ResponseEntity<Usuario> guardar(@Valid @RequestBody Usuario usuario){

    Usuario nueva = service.guardar(usuario);

    return ResponseEntity.status(201).body(nueva);
}   

    @DeleteMapping("eliminar/{id}")
    public String eliminar(@PathVariable Integer id){
        Optional<Usuario> usuario = service.buscarPorId(id);
        if(usuario.isPresent()){   
            service.eliminarPorId(id);
            return "El Usuario fue eliminado con éxito";
        }else {
            return "Usuario no encontrada con id: " + id;
        }
    }

    @PutMapping("actualizar/{id}")
    public String actualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
        
        Optional<Usuario> existente = service.buscarPorId(id);
        if(existente.isPresent()){
            service.actualizarUsuario(id, usuario);
            return "Usuario Actualizado correctamente";
        }else {
            return "Usuario no encontrado con id: "+id;
        }
        
    }
}

