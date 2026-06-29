package com.promociones.descuento.Service;

import com.promociones.descuento.Model.DescuentoModel;
import com.promociones.descuento.dto.PromocionReqDTO;
import com.promociones.descuento.dto.PromocionRespDto;
import java.util.List;

public interface DescuentoService {
    DescuentoModel guardarDescuento(DescuentoModel descuento);
    List<PromocionReqDTO> listarDTO();
    PromocionRespDto obtenerDetalleDescuento(Integer productoId);
}