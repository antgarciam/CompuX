package com.resenas.comentario;
import com.resenas.comentario.model.Comentario;
import com.resenas.comentario.repository.ComentarioRepositorio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean 
    CommandLineRunner init(ComentarioRepositorio repository){
    return args -> {
        if(repository.count() == 0){
            repository.save(new Comentario(null, "Ejemplo De Titulo", "Ejemplo De Texto"));
            }
        };
    }
}