package com.promociones.descuento.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "descuentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DescuentoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "El ID del producto es obligatorio")
    @Column(name = "producto_id", nullable = false)
    private Integer productoId;

    @NotBlank(message = "La descripción de la oferta no puede estar vacía")
    @Column(nullable = false, length = 150)
    private String descripcion;

    @NotBlank(message = "El tipo de descuento es obligatorio")
    @Pattern(regexp = "PORCENTAJE|FIJO", message = "El tipo debe ser 'PORCENTAJE' o 'FIJO'")
    private String tipoDescuento;

    @NotNull(message = "El valor del descuento no puede ser nulo")
    @Positive(message = "El valor del descuento debe ser un número positivo")
    private Double valor;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDateTime fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    private LocalDateTime fechaFin;

    @NotNull(message = "El estado activo es obligatorio")
    private Boolean activo;
}