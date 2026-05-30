package com.notificaciones.mensaje.Service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import com.notificaciones.mensaje.Model.mensajeModel;
import com.notificaciones.mensaje.Repository.*;
import com.notificaciones.mensaje.DTO.mensajeDTO;

import java.util.List;

@Service
public class mensajeService {

    @Autowired
    private mensajeRepository repository;

    public mensajeModel guardarMensaje(mensajeDTO DTO){

        mensajeModel mensaje = new mensajeModel();

        mensaje.setNombre(DTO.getNombre());
        mensaje.setCategoria(DTO.getCategoria());
        mensaje.setContenido(DTO.getContenido());

        return repository.save(mensaje);
        
    }

    public List<mensajeModel> obtenerTodosLosMensajes() {
        return repository.findAll();
    }
}
