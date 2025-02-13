package com.stevedev.productosmservice.services;

import com.stevedev.productosmservice.dto.request.ProductoReq;
import com.stevedev.productosmservice.dto.response.ProductoRes;

import java.util.List;

public interface IProductoService {
    List<ProductoRes> getAllProductos();
    ProductoRes getProductoById(Long id);
    ProductoRes create(ProductoReq req);
    ProductoRes update(Long id, ProductoReq req);
    void delete(Long id);

    List<ProductoRes> getProductosByNombreOrDescripcion(String term);
    List<ProductoRes> getAllProductosOrderedByName();
    List<ProductoRes> getProductosByPriceRange(Double min, Double max);
}
