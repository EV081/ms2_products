package com.example.ms2_productos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoResponseDTO {

    private Long idProducto;
    private String nombre;
    private String descripcion;
    private double precio;
    private CategoriaDTO categoria;
}