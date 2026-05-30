package com.ventas.informe.service;

import com.ventas.informe.model.Venta;
import com.ventas.informe.repository.VentaRepositorio;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class VentaServicio {

    private final VentaRepositorio repositorio;

    public VentaServicio(VentaRepositorio repositorio){
        this.repositorio = repositorio;
    }

    public Venta guardarVenta(Venta venta){
        return repositorio.save(venta);
    }


      //GET; Buscar por NOMBRE
    public Optional<Venta>buscarPorNombreProducto(String nombreProducto){
        return repositorio.findByNombreProductoIgnoreCase(nombreProducto);
    }

    //GET; Buscar por ID
    public Optional<Venta> buscarPorId(Integer Id){
        return repositorio.findById(Id);
    }


    public void eliminarPorId(Integer Id){
        repositorio.deleteById(Id);
    }

    public Venta actualizarVenta(Integer id, Venta venta){
        venta.setId(id);
        return repositorio.save(venta);
    }




    

}


