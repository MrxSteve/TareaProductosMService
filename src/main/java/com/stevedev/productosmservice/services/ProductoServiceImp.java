package com.stevedev.productosmservice.services;

import com.stevedev.productosmservice.dto.request.ProductoReq;
import com.stevedev.productosmservice.dto.response.ProductoRes;
import com.stevedev.productosmservice.entity.ProductoEntity;
import com.stevedev.productosmservice.repositories.ProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductoServiceImp implements IProductoService {
    private final ProductoRepository productoRepository;

    @Override
    public List<ProductoRes> getAllProductos() {
        List<ProductoEntity> entities = productoRepository.findAll();
        if (entities.isEmpty()) {
            throw new NoSuchElementException("No se encontraron productos");
        }
        return entities.stream().map(this::mapToResponse).toList();
    }

    @Override
    public ProductoRes getProductoById(Long id) {
        ProductoEntity entity = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
        return mapToResponse(entity);
    }

    @Override
    public ProductoRes create(ProductoReq req) {
        ProductoEntity entity = mapToEntity(req);
        ProductoEntity savedEntity = productoRepository.save(entity);
        return mapToResponse(savedEntity);
    }

    @Override
    public ProductoRes update(Long id, ProductoReq req) {
        ProductoEntity entity = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

        if (req.getNombre() != null) {
            entity.setNombre(req.getNombre());
        }
        if (req.getDescripcion() != null) {
            entity.setDescripcion(req.getDescripcion());
        }
        if (req.getPrecio() != null) {
            entity.setPrecio(req.getPrecio());
        }
        if (req.getStock() != null) {
            entity.setStock(req.getStock());
        }

        ProductoEntity updatedEntity = productoRepository.save(entity);
        return mapToResponse(updatedEntity);
    }

    @Override
    public void delete(Long id) {
        ProductoEntity entity = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
        productoRepository.delete(entity);
    }

    @Override
    public List<ProductoRes> getProductosByNombreOrDescripcion(String term) {
        List<ProductoEntity> entities = productoRepository.findByNombreContainingIgnoreCaseOrDescripcionContainingIgnoreCase(term, term);
        if (entities.isEmpty()) {
            throw new NoSuchElementException("No se encontraron productos con el término: " + term);
        }
        return entities.stream().map(this::mapToResponse).toList();
    }

    @Override
    public List<ProductoRes> getAllProductosOrderedByName() {
        List<ProductoEntity> entities = productoRepository.findAllByOrderByNombre();
        if (entities.isEmpty()) {
            throw new NoSuchElementException("No se encontraron productos");
        }
        return entities.stream().map(this::mapToResponse).toList();
    }

    @Override
    public List<ProductoRes> getProductosByPriceRange(Double min, Double max) {
        List<ProductoEntity> entities = productoRepository.findByPrecioBetween(min, max);
        if (entities.isEmpty()) {
            throw new NoSuchElementException("No se encontraron productos en el rango de precios: " + min + " - " + max);
        }
        return entities.stream().map(this::mapToResponse).toList();
    }

    private ProductoRes mapToResponse(ProductoEntity entity) {
        ProductoRes res =new ProductoRes();
        res.setId(entity.getId());
        res.setNombre(entity.getNombre());
        res.setDescripcion(entity.getDescripcion());
        res.setPrecio(entity.getPrecio());
        res.setStock(entity.getStock());
        res.setCreatedAt(entity.getCreatedAt());
        res.setUpdatedAt(entity.getUpdatedAt());
        return res;
    }

    private ProductoEntity mapToEntity(ProductoReq req) {
        ProductoEntity entity = new ProductoEntity();
        entity.setNombre(req.getNombre());
        entity.setDescripcion(req.getDescripcion());
        entity.setPrecio(req.getPrecio());
        entity.setStock(req.getStock());
        return entity;
    }
}
