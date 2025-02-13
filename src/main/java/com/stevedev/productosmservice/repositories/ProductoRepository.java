package com.stevedev.productosmservice.repositories;

import com.stevedev.productosmservice.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
    // Crea una ruta para buscar y devolver una lista de productos cuyo nombre o descripción contengan un término específico.
    List<ProductoEntity> findByNombreContainingIgnoreCaseOrDescripcionContainingIgnoreCase(String nombre, String descripcion);
    //Define una ruta que te devuelva la lista de todos los productos ordenados alfabéticamente
    List<ProductoEntity> findAllByOrderByNombre();
    //Crea una ruta para buscar y devolver una lista de productos cuyos precios se encuentre en un rango dado por la petición
    List<ProductoEntity> findByPrecioBetween(Double min, Double max);
}