package com.inventario.productos.Model;

import jakarta.persistence.*;               // Permite mapear la clase a una tabla en la base de atos
import lombok.AllArgsConstructor;           //Genera automaticamente un construcotr con todos los atributos 
import lombok.Data;                         //Genera getters, setters, toString, equals, y haschCode autoamticamente
import lombok.NoArgsConstructor;            //Genera un constructor vacio sin parametros
import jakarta.validation.constraints.*;    // permite validar los datos (Ej: campos obligatorios valores minimos, etc.)

@Entity                                     // Indica que esta clase es una entidad que se mapeara a una tabla en la BD
@Table(name="producto")                     // Define el nombre de la tabla en la base datos
@Data                                       //Genera autoamticamente getters, setters, ToString,Eqquals y hashCode
@NoArgsConstructor                          //Genera un constructor vacio necesario para JPA
@AllArgsConstructor                         //

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
