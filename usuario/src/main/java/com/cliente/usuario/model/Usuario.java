package com.cliente.usuario.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;              
import lombok.AllArgsConstructor;          
import lombok.Data;                        
import lombok.NoArgsConstructor;           
import jakarta.validation.constraints.*;   

@Entity                                    
@Table(name="usuario")                    
@Data                                      
@NoArgsConstructor                         
@AllArgsConstructor                        

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre no puede estar vacio")
    @Column(nullable = false, length = 60)
    private String nombreUsuario;
    
    @NotBlank(message = "El apellido no puede estar vacio")
    @Column(nullable = false, length = 60)
    private String apellidoUsuario;


    @NotBlank(message ="El correo no puede estar vacio")
    private String correoUsuario;
    
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;
}