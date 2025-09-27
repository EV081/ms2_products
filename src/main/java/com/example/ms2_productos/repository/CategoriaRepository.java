package com.example.ms2_productos.repository;

import com.example.ms2_productos.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}