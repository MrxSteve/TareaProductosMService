package com.stevedev.productosmservice.controllers;

import com.stevedev.productosmservice.dto.request.ProductoReq;
import com.stevedev.productosmservice.dto.response.ProductoRes;
import com.stevedev.productosmservice.services.IProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {
    private final IProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoRes>> getAll() {
        List<ProductoRes> res = productoService.getAllProductos();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoRes> getById(@PathVariable Long id) {
        ProductoRes res = productoService.getProductoById(id);
        return ResponseEntity.ok(res);
    }

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody ProductoReq req) {
        productoService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body("Producto creado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,@Valid @RequestBody ProductoReq req) {
        productoService.update(id, req);
        return ResponseEntity.ok("Producto actualizado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        productoService.delete(id);
        return ResponseEntity.ok("Producto eliminado");
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductoRes>> findByNombreOrDescripcion(@RequestParam String term) {
        List<ProductoRes> res = productoService.getProductosByNombreOrDescripcion(term);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/order")
    public ResponseEntity<List<ProductoRes>> findByOrderByNombre() {
        List<ProductoRes> res = productoService.getAllProductosOrderedByName();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/price")
    public ResponseEntity<List<ProductoRes>> findByPriceRange(@RequestParam Double min, @RequestParam Double max) {
        List<ProductoRes> res = productoService.getProductosByPriceRange(min, max);
        return ResponseEntity.ok(res);
    }
}
