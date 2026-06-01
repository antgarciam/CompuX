package com.ventas.informe.service;

import com.ventas.informe.dto.VentaListadoDTO;
import com.ventas.informe.dto.VentaSimpleDTO;
import com.ventas.informe.model.Venta;
import com.ventas.informe.repository.VentaRepositorio;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.ArrayList;



@Service
public class VentaServicio {

    private final VentaRepositorio repository;

    public VentaServicio(VentaRepositorio repositorio){
        this.repository = repositorio;
    }

    public Venta guardarVenta(Venta venta){
        return repository.save(venta);
    }


    public List<Venta> obtenerVentas(Integer codProducto){

    Optional<Venta> ventaOpt = repository.findById(codProducto);
    if(ventaOpt.isEmpty()){
        return new ArrayList<>();
    }
    List<Venta> ventas = new ArrayList<>();
    return ventas;  
    }
  public List<VentaListadoDTO> ListarDTO(){
        List<Venta> ventas = repository.findAll(); 
        List<VentaListadoDTO> lista = new ArrayList<>(); 

        for(Venta p : ventas){   
            VentaListadoDTO dto = new VentaListadoDTO(); 
            dto.setNombreProducto(p.getNombreProducto()); 
            dto.setCantidad(p.getCantidad());

            lista.add(dto);  
        }
        return lista; 
    }
     
    public VentaSimpleDTO obtenerDetalleSimple(Integer codProducto){

        Optional<Venta> ventaOpt = repository.findById(codProducto);
        if(ventaOpt.isEmpty()){ 
            return null;
        }

        Venta venta = ventaOpt.get();
        
        List<Venta> ventas = obtenerVentas(codProducto);

        
        List<String> nombreProductos = new ArrayList<>();

        for (Venta p : ventas){
            nombreProductos.add(p.getNombreProducto()); 
        }

        VentaSimpleDTO dto = new VentaSimpleDTO();

        dto.setNombreProducto(venta.getNombreProducto());
        dto.setCantidad(venta.getCantidad());
        dto.setValor(venta.getValor());

        return dto;  
    
        }
    

}



    




