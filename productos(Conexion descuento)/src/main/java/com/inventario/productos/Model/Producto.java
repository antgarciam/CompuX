package com.inventario.productos.Model;

import jakarta.persistence.*;               
import lombok.AllArgsConstructor;           
import lombok.Data;                         
import lombok.NoArgsConstructor;           
import jakarta.validation.constraints.*;    

@Entity                                     
@Table(name="producto")                    
@Data                                       
@NoArgsConstructor                          
@AllArgsConstructor                         

public class Producto {
 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre no puede estar vacio")
    @Column(nullable = false, length = 100)
    private String NombreProducto;
    
    @Positive(message = "El Stock debe de estar positivo")
    @NotNull(message = "El stock no puede estar vacio")
    private Integer Stock;

    @Positive(message = "El precio no puede ser menor a 0")
    @NotNull(message = "Debe de tener un precio")
    private Integer Precio;

    @NotBlank(message ="El tipo no puede estar vacio")
    private String categoria;
}
