package com.example.ms2_productos.service;

import com.example.ms2_productos.domain.Categoria;
import com.example.ms2_productos.domain.Producto;
import com.example.ms2_productos.repository.CategoriaRepository;
import com.example.ms2_productos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public Page<Categoria> obtenerCategorias(Pageable pageable) {
        return categoriaRepository.findAll(pageable);
    }

    // 2) Productos de una categoría paginados
    public Page<Producto> obtenerProductosPorCategoria(Long idCategoria, Pageable pageable) {
        return productoRepository.findByCategoria_IdCategoria(idCategoria, pageable);
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
