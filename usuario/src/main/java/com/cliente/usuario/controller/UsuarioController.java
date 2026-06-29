package com.cliente.usuario.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cliente.usuario.dto.UsuarioListadoDTO;
import com.cliente.usuario.model.Usuario;
import com.cliente.usuario.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.Optional;

import com.cliente.usuario.dto.ProductoListadoDTO;


@RestController

@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

        @Operation(
        summary = "Listar Usuarios",
        description = "Listar todos los Usuarios"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})

    @GetMapping("/listar")
    public List<Usuario> listar(){
        return service.listar();
    }

        @Operation(
        summary = "Listar dto",
        description = "Listar todo los DTOs "
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})

    @GetMapping("/listar-dto")
    public List<UsuarioListadoDTO> listarDTO(){
        return service.listarDTO();
}

        @Operation(
        summary = "Buscar Por id",
        description = "..."
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})

    @GetMapping("buscar/{id}")
    public Optional<Usuario> buscarPorId(@PathVariable Integer id){
        return service.buscarPorId(id);
    }

        @Operation(
        summary = "Buscar por nombre de usuario",
        description = "..."
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})

    @GetMapping("nombre/{nombreUsuario}")
    public List<Usuario> buscarPorNombreUsuario(@PathVariable String nombreUsuario){
        return service.buscarPorNombreUsuario(nombreUsuario);
    }

        @Operation(
        summary = "Agregar Usuarios",
        description = "..."
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})

    @PostMapping("/agregar")
    public Usuario guardar(@Valid @RequestBody Usuario usuario){

    return service.guardar(usuario);
}   

        @Operation(
        summary = "eliminar Usuario",
        description = "..."
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})

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

        @Operation(
        summary = "actualizar Usuario",
        description = "..."
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})

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

        @Operation(
        summary = "Catalogo",
        description = "..."
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")  ,
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")        
})

    @GetMapping("/catalogo")
    public List<ProductoListadoDTO> verCatalogo(){
        return service.obtenerCatalogoDeProductos();
    }
}

