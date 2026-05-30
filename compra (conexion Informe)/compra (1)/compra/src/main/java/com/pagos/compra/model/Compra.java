package com.pagos.compra.model;

import jakarta.persistence.*;               // Permite mapear la clase a una tabla en la base de atos
import lombok.AllArgsConstructor;           //Genera automaticamente un construcotr con todos los atributos 
import lombok.Data;                         //Genera getters, setters, toString, equals, y haschCode autoamticamente
import lombok.NoArgsConstructor;            //Genera un constructor vacio sin parametros
import jakarta.validation.constraints.*;    // permite validar los datos (Ej: campos obligatorios valores minimos, etc.)

@Entity                                     // Indica que esta clase es una entidad que se mapeara a una tabla en la BD
@Table(name="compra")                     // Define el nombre de la tabla en la base datos
@Data                                       //Genera autoamticamente getters, setters, ToString,Eqquals y hashCode
@NoArgsConstructor                          //Genera un constructor vacio necesario para JPA
@AllArgsConstructor                         //

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
