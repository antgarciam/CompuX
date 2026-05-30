package com.inventario.productos.Service;

import com.inventario.productos.Repository.ProductoRepository;
import com.inventario.productos.dto.ProductoListadoDTO;
import com.inventario.productos.dto.ProductoPromocionDTO;
import com.inventario.productos.dto.PromocionDTO;
import com.inventario.productos.Model.Producto;
import org.springframework.beans.factory.annotation.Autowired; //Permite la inyeccion de dependecias spring crea el objeto
import org.springframework.stereotype.Service; //Indica que la clase es un componente de tipo servicio dentro del spring
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class ProductoService {
    @Autowired
    private ProductoRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public ProductoPromocionDTO obtenerProductoConPromocion(Integer id) {

       
        Producto producto = repository.findById(id)
                .orElseThrow();

        
        PromocionDTO promo = restTemplate.getForObject(
                "http://localhost:8083/promociones/" + id,
                PromocionDTO.class
        );

        
        ProductoPromocionDTO dto = new ProductoPromocionDTO();

        dto.setId(producto.getId());
        dto.setNombreProducto(producto.getNombreProducto());
        dto.setPrecio(producto.getPrecio());

        if (promo != null) {
            dto.setValor(promo.getValor());
            dto.setPrecioFinal(
                    producto.getPrecio() * promo.getValor()
            );
        } else {
            dto.setValor(0.0);
            dto.setPrecioFinal(producto.getPrecio());
        }

        return dto;
    }


    //GET; Listar TODOS
    public List<Producto> listar(){
        return repository.findAll();
    }

    //GET; Buscar por ID
    public Optional<Producto> buscarPorId(Integer id){
        return repository.findById(id);
    }
    
    //POST; Guardar 
    public Producto guardar(Producto producto) {
        return repository.save(producto);
    }

    //DELETE; Eliminar
    public void eliminarPorId(Integer id){
        repository.deleteById(id);
    }

    //POST; Actualizar
    public Producto actualizarProducto(Integer id, Producto producto){
        producto.setId(id);
        return repository.save(producto);
    }
    
    public List<Producto> buscarPorCategoria(String categoria){
        return repository.findByCategoriaIgnoreCase(categoria);
    }

     public List<ProductoListadoDTO> listarDTO(){
        List<Producto> producto = repository.findAll(); // Se obtienen todas las entidades Producto desde la base de datos
        List<ProductoListadoDTO> lista = new ArrayList<>(); // Se crea la lista que contendra los DTO que se devolveran

        for(Producto p : producto){   // Se recorre cada entidad Producto para transformarla en un DTO
            ProductoListadoDTO dto = new ProductoListadoDTO(); // Se crea una nueva instancia del DTO
            dto.setNombreProducto(p.getNombreProducto());
            dto.setStock(p.getStock());
            dto.setPrecio(p.getPrecio()); // Se copian solo los datos necesarios desde la entidad al DTO
            dto.setCategoria(p.getCategoria());

            lista.add(dto); // Se agrega el DTO a la lista de resultados
        }
        return lista; // Se retorna la lista de DTOs en lugar de las entidades
    }

    public void restarStock(Integer productoId, Integer cantidad){
        Optional<Producto> buscarProducto = repository.findById(productoId);

        if (buscarProducto.isPresent()){
            Producto producto = buscarProducto.get();

            Integer stockActual = producto.getStock();
            producto.setStock(stockActual - cantidad);

            repository.save(producto);
            System.out.println("Stock actualizado correctamente");
        } else {
            System.out.println("El producto con ID " + productoId + " no existe");
        }
    }


}
