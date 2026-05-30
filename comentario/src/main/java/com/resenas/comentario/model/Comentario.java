package com.resenas.comentario.model;

import jakarta.persistence.*;               
import lombok.AllArgsConstructor;           
import lombok.Data;                         
import lombok.NoArgsConstructor;            
import jakarta.validation.constraints.*;

@Entity                                     
@Table(name="Comentario")                   
@Data                                       
@NoArgsConstructor                          
@AllArgsConstructor  

public class Comentario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @NotBlank(message = "El titulo no opuede estar vacio")
    @Column(nullable = false, length = 100)
    private String Titulo;

    @NotBlank(message = "El Comentario no opuede estar vacio")
    @Column(nullable = false, length = 100000)
    private String Texto;
    

}
