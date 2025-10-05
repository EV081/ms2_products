package com.example.ms2_productos.repository;

import com.example.ms2_productos.domain.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Page<Producto> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);

    Page<Producto> findByCategoria_IdCategoria(Long idCategoria, Pageable pageable);

    Page<Producto> findByCategoria_IdCategoriaAndNombreContainingIgnoreCase(Long idCategoria, String nombre, Pageable pageable);
}
