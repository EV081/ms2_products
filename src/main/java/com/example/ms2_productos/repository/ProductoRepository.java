package com.example.ms2_productos.repository;

import com.example.ms2_productos.domain.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Page<Producto> findByCategoria_IdCategoria(Long idCategoria, Pageable pageable);

}