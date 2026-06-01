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

    @NotNull(message = "El ID del usuario   es obligatorio")
    private Integer usuarioId;

    @NotNull(message = "El ID del producto es obligatorio")
    private Integer productoId;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a cero")
    private Integer cantidad;

    @NotNull(message = "El monto no puede estar vacío")
    @Positive(message = "El monto debe ser positivo")
    private Integer monto;

    @NotBlank(message = "El método de pago no puede estar vacío")
    private String metodoPago;

    @NotBlank(message = "El estado del pago no puede estar vacío")
    private String estadoPago;

}
