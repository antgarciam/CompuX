package com.pagos.compra.model;

import jakarta.persistence.*;              
import lombok.AllArgsConstructor;           
import lombok.Data;                        
import lombok.NoArgsConstructor;    
import jakarta.validation.constraints.*;    

@Entity                                     
@Table(name="compra")                 
@Data                                       
@NoArgsConstructor                      
@AllArgsConstructor      

public class Compra {
 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El metodo de pago no puede estar vacio")
    @Column(nullable = false, length = 100)
    private String metodoPago;

    @NotBlank(message ="El estado del pago no puede estar vacio")
    private String estadoPago;

    @Positive(message = "El monto debe ser positivo")
    @NotNull(message = "El monto no puede estar vacio")
    private Integer monto;

    @NotNull(message = "El ID del producto es obligatorio")
    private Integer idProducto;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a cero")
    private Integer cantidad;

}
