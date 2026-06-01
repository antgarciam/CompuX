package com.ventas.informe;

import com.ventas.informe.model.Venta;
import com.ventas.informe.repository.VentaRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean 
    CommandLineRunner init(VentaRepositorio repository){
    return args -> {
        if(repository.count() == 0){
        repository.save(new Venta(null, "Nombre del producto", 10, 100000));
            }

        };
    }

}
