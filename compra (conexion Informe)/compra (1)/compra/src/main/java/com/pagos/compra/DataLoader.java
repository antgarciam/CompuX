package com.pagos.compra;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pagos.compra.model.Compra;
import com.pagos.compra.repository.CompraRepository;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner init(CompraRepository repository){
        return args -> {
            if(repository.count() == 0 ){
                repository.save(new Compra(null,"Debito", "Pendiente" , 59000,1,1 ));
                repository.save(new Compra(null, "Credito", "Aprobado", 193000,2,3));
                repository.save(new Compra(null, "PayPal", "Rechazado",283000,1,2));
                repository.save(new Compra(null, "Credito", "Cancelado", 1200000,3,1));
                repository.save(new Compra(null, "MercadoPago", "Aprobado", 1200000,2,2));
            }
        };
    }

}
