package com.stevedev.productosmservice.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductoRes {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}