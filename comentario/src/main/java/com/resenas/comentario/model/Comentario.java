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
    private Integer id;

    @NotNull
    private Integer usuarioId;

    @NotNull
    private Integer productoId;

    @NotBlank
    private String comentario;

    @Min(1)
    @Max(10)
    private Integer calificacion;
    

}
