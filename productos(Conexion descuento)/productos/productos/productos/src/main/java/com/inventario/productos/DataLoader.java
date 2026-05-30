package com.inventario.productos;

    import com.inventario.productos.Model.Producto;
    import com.inventario.productos.Repository.ProductoRepository;
    import org.springframework.boot.CommandLineRunner;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;

    @Configuration
    public class DataLoader {
        @Bean
        CommandLineRunner init(ProductoRepository repository){
            return args -> {
                if(repository.count() == 0 ){
                    repository.save(new Producto(null,"GTX 2060", 6 , 230400,"GPU"));
                    repository.save(new Producto(null,"Monitor Asus 240Hz", 20 , 190000,"Monitor"));
                    repository.save(new Producto(null,"Mouse Logitech", 24 , 40000,"Perifericos"));
                    repository.save(new Producto(null,"RX 6700", 10 , 320500,"GPU"));
                }
            };
        }

    }
