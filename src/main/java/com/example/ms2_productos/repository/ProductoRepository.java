package com.example.ms2_productos.repository;


import com.example.ms2_productos.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}