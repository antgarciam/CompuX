package com.notificaciones.mensaje.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "mensaje")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class mensajeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;

    
    private String categoria;

    
    @Column(nullable = false, length = 300)
    private String contenido;

    @CreationTimestamp
    @Column(name = "fecha envio", nullable = false, updatable = false)
    private LocalDateTime fechaEnvio;


}
