package com.pagos.compra.service;


import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 

import com.pagos.compra.dto.ActualizarStockDTO;
import com.pagos.compra.dto.CompraInformeDTO;
import com.pagos.compra.dto.CompraListadoDTO;
import com.pagos.compra.dto.CompraRequestDTO;
import com.pagos.compra.dto.CompraResponseDTO;
import com.pagos.compra.dto.NotificacionDTO;
import com.pagos.compra.dto.ProductoDTO;
import com.pagos.compra.dto.PromocionDTO;
import com.pagos.compra.model.Compra;
import com.pagos.compra.repository.CompraRepository;

import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class CompraService {
    @Autowired
    private CompraRepository repository;

    private final RestTemplate restTemplate =
            new RestTemplate();

    public CompraResponseDTO realizarCompra(
            CompraRequestDTO dto){

       
        ProductoDTO producto =
                restTemplate.getForObject(
                        "http://localhost:8081/productos/buscar/"
                                + dto.getProductoId(),
                        ProductoDTO.class
                );

        if(producto == null){
            throw new RuntimeException(
                    "Producto no encontrado"
            );
        }

        PromocionDTO promo =
                restTemplate.getForObject(
                        "http://localhost:8083/descuentos/"
                                + dto.getProductoId()
                                + "/detalle-descuento",
                        PromocionDTO.class
                );

        if(promo == null){
            throw new RuntimeException(
                    "No se pudo obtener información de promociones"
            );
        }

        Compra compra = new Compra();

        compra.setUsuarioId(
                dto.getUsuarioId()
        );

        compra.setProductoId(
                dto.getProductoId()
        );

        compra.setCantidad(
                dto.getCantidad()
        );

        compra.setMonto(
                promo.getPrecioFinal().intValue()
        );

        compra.setMetodoPago(
                "Tarjeta"
        );

        compra.setEstadoPago(
                "APROBADO"
        );

        repository.save(compra);

        CompraResponseDTO respuesta =
                new CompraResponseDTO();

        respuesta.setUsuarioId(
                compra.getUsuarioId()
        );

        respuesta.setProductoId(
                compra.getProductoId()
        );

        respuesta.setCantidad(
                compra.getCantidad()
        );

        respuesta.setPrecioOriginal(
                promo.getPrecioOriginal()
        );

        respuesta.setDescuentoAplicado(
                promo.getDescuentoAplicado()
        );

        respuesta.setPrecioFinal(
                promo.getPrecioFinal()
        );

        respuesta.setDescripcionPromocion(
                promo.getDescripcion()
        );

        respuesta.setEstadoPago(
                compra.getEstadoPago()
        );

        return respuesta;
    }


    public CompraInformeDTO ObtenerCompraeInforme(Integer id){

        Compra compra = repository.findById(id)
            .orElseThrow();

        CompraInformeDTO dto = new CompraInformeDTO();

        dto.setId(compra.getId());
        dto.setMetodoPago(compra.getMetodoPago());
        dto.setMonto(compra.getMonto());

        return dto;

    }

    private final RestClient restClientNotificaciones = RestClient.builder()
    .baseUrl("http://localhost:8085")
    .build();

    private final RestClient restClientInventario = RestClient.builder()
    .baseUrl("http://localhost:8080/productos")
    .build();

    public Compra guardar(Compra Compra) {

        Compra compraGuardada = repository.save(Compra);

        try{
            ActualizarStockDTO stockDTO = new ActualizarStockDTO();
            stockDTO.setProductoId(Compra.getProductoId());
            stockDTO.setCantidad(Compra.getCantidad());

            restClientInventario.post()
                .uri("/descontar")
                .body(stockDTO)
                .retrieve()
                .toBodilessEntity();
                System.out.println("Inventario actualizado correctamente");
                } catch (Exception e) {
                    System.out.println("Error al actualziar el inventario");
                }

        try{
            
            NotificacionDTO dto = new NotificacionDTO();
            dto.setNombre("Sistema de pagos");
            dto.setCategoria("Comprobante");
            dto.setContenido("Tu pago de $" + compraGuardada.getMonto() + " con metodo " + compraGuardada.getMetodoPago() + "fue procesado con exito");

            restClientNotificaciones.post()
                .uri("/api/mensajes")
                .body(dto)
                .retrieve()
                .toBodilessEntity();

            System.out.println("Pago Registrado correctamente");
        } catch (Exception e) {
            System.out.println("No se pudo registrar el pago" + e.getMessage());
            
        }
        return compraGuardada;
    }

   
    
    public List<Compra> listar(){
        return repository.findAll();
    }

    public Optional<Compra> buscarPorId(Integer id){
        return repository.findById(id);
    }
    


    public void eliminarPorId(Integer id){
        repository.deleteById(id);
    }

    
    public Compra actualizarCompra(Integer id, Compra compra){
        compra.setId(id);
        return repository.save(compra);
    }
    
    public List<Compra> buscarPorMetodoPago(String metodoPago){
        return repository.findByMetodoPagoIgnoreCase(metodoPago);
    }

    public List<Compra> buscarPorEstadoPago(String estadoPago){
        return repository.findByEstadoPagoIgnoreCase(estadoPago);
    }

     public List<CompraListadoDTO> listarDTO(){
        List<Compra> compra = repository.findAll(); 
        List<CompraListadoDTO> lista = new ArrayList<>();

        for(Compra p : compra){   
            CompraListadoDTO dto = new CompraListadoDTO(); 
            dto.setMonto(p.getMonto());
            dto.setMetodoPago(p.getMetodoPago());
            dto.setEstadoPago(p.getEstadoPago()); 

            lista.add(dto); 
        }
        return lista; 
    }   

}
