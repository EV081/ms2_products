package com.example.ms2_productos.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoRequestDTO {

    private String nombre;
    private String descripcion;
    private double precio;
    private Long idCategoria;
}
