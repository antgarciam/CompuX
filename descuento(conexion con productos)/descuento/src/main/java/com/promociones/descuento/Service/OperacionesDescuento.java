package com.promociones.descuento.Service;

import com.promociones.descuento.Model.DescuentoModel;
import com.promociones.descuento.Repository.DescuentoRepository;
import com.promociones.descuento.dto.PromocionReqDTO;
import com.promociones.descuento.dto.PromocionRespDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OperacionesDescuento implements DescuentoService {

    private final DescuentoRepository repository;
    private final RestTemplate restTemplate;

    // Inyectamos el repositorio y el RestTemplate para llamadas HTTP
    public OperacionesDescuento(DescuentoRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    @Override
    public DescuentoModel guardarDescuento(DescuentoModel descuento) {
        return repository.save(descuento);
    }

    @Override
    public List<PromocionReqDTO> listarDTO() {
        List<DescuentoModel> descuentos = repository.findAll();
        List<PromocionReqDTO> listaDto = new ArrayList<>();

        for (DescuentoModel d : descuentos) {
            PromocionReqDTO dto = new PromocionReqDTO();
            dto.setProductoId(d.getProductoId());
            dto.setDescripcion(d.getDescripcion());
            dto.setTipoDescuento(d.getTipoDescuento());
            dto.setValor(d.getValor());
            listaDto.add(dto);
        }
        return listaDto;
    }

    @Override
    public PromocionRespDto obtenerDetalleDescuento(Integer productoId) {
        
        // 1. LLAMADA HTTP AL MICROSERVICIO PRODUCTO
        Double precioBaseInventario = 0.0;
        try {
            // IMPORTANTE: Cambia "http://localhost:8080" por la URL y puerto donde corra tu microservicio PRODUCTO.
            String urlProducto = "http://localhost:8080/productos/" + productoId + "/precio";
            
            // Hacemos una petición GET esperando un Double de respuesta
            precioBaseInventario = restTemplate.getForObject(urlProducto, Double.class);
            
            if (precioBaseInventario == null) {
                precioBaseInventario = 0.0; // Valor por defecto si el API no devuelve nada
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al comunicarse con el microservicio PRODUCTO: " + e.getMessage());
        }

        // 2. LÓGICA DE DESCUENTOS (Tu código original corregido)
        LocalDateTime ahora = LocalDateTime.now();
        List<DescuentoModel> descuentosDelProducto = repository.findByProductoId(productoId);
        DescuentoModel descuentoVigente = null;

        for (DescuentoModel d : descuentosDelProducto) {
            if (d.getActivo() && ahora.isAfter(d.getFechaInicio()) && ahora.isBefore(d.getFechaFin())) {
                descuentoVigente = d;
                break; 
            }
        }

        PromocionRespDto respuesta = new PromocionRespDto();
        respuesta.setProductoId(productoId);
        respuesta.setPrecioOriginal(precioBaseInventario);

        if (descuentoVigente == null) {
            respuesta.setDescripcion("Sin ofertas vigentes hoy");
            respuesta.setDescuentoAplicado(0.0);
            respuesta.setPrecioFinal(precioBaseInventario);
            return respuesta;
        }

        respuesta.setDescripcion(descuentoVigente.getDescripcion());
        Double rebaja = 0.0;

        if (descuentoVigente.getTipoDescuento().equalsIgnoreCase("PORCENTAJE")) {
            rebaja = precioBaseInventario * (descuentoVigente.getValor() / 100);
        } else if (descuentoVigente.getTipoDescuento().equalsIgnoreCase("FIJO")) {
            rebaja = descuentoVigente.getValor();
        }

        respuesta.setDescuentoAplicado(rebaja);
        respuesta.setPrecioFinal(precioBaseInventario - rebaja);

        return respuesta;
    }
}