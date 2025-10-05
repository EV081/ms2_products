package com.example.ms2_productos.service;

import com.example.ms2_productos.domain.Categoria;
import com.example.ms2_productos.domain.Producto;
import com.example.ms2_productos.repository.CategoriaRepository;
import com.example.ms2_productos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public Page<Categoria> obtenerCategorias(Pageable pageable, String search) {
        if (search == null || search.isBlank()) {
            return categoriaRepository.findAll(pageable);
        }
        return categoriaRepository.findByNombreCategoriaContainingIgnoreCase(search.trim(), pageable);
    }

    public Page<Producto> obtenerProductosPorCategoria(Long idCategoria, Pageable pageable, String search) {
        String q = (search == null) ? null : search.trim();
        if (q == null || q.isBlank()) {
            return productoRepository.findByCategoria_IdCategoria(idCategoria, pageable);
        }
        return productoRepository.findByCategoria_IdCategoriaAndNombreContainingIgnoreCase(idCategoria, q, pageable);
    }

    public Optional<Categoria> obtenerCategoriaPorId(Long idCategoria) {
        return categoriaRepository.findById(idCategoria);
    }

    public Categoria crearCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria actualizarCategoria(Long idCategoria, Categoria categoria) {
        if (categoriaRepository.existsById(idCategoria)) {
            categoria.setIdCategoria(idCategoria);
            return categoriaRepository.save(categoria);
        }
        return null;
    }

    public boolean eliminarCategoria(Long idCategoria) {
        if (categoriaRepository.existsById(idCategoria)) {
            categoriaRepository.deleteById(idCategoria);
            return true;
        }
        return false;
    }
}
