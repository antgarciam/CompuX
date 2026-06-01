package com.ventas.informe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;


@Entity
@Table(name = "venta")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codProducto;

    @NotBlank(message = "El producto no puede estar en blanco")
    @Column(nullable = false, length = 100)
    private String nombreProducto;

    @NotNull
    @Positive(message = "Debe ingresar la cantidad")
    private Integer cantidad;

    @NotBlank(message = "El valor no puede estar vacío")
    private Integer valor;

}


