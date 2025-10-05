package com.example.ms2_productos.service;

import com.example.ms2_productos.domain.dto.CategoriaDTO;
import com.example.ms2_productos.domain.dto.ProductoRequestDTO;
import com.example.ms2_productos.domain.dto.ProductoResponseDTO;
import com.example.ms2_productos.domain.Categoria;
import com.example.ms2_productos.domain.Producto;
import com.example.ms2_productos.repository.CategoriaRepository;
import com.example.ms2_productos.repository.ProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public ProductoResponseDTO convertirAProductoResponseDTO(Producto producto) {
        Categoria categoria = producto.getCategoria();
        CategoriaDTO categoriaDTO = new CategoriaDTO(
                categoria.getIdCategoria(),
                categoria.getNombreCategoria(),
                categoria.getDescripcionCategoria()
        );

        return new ProductoResponseDTO(
                producto.getIdProducto(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                categoriaDTO
        );
    }

    public Page<Producto> obtenerProductos(Pageable pageable, String search) {
        if (search == null || search.isBlank()) {
            return productoRepository.findAll(pageable);
        }
        return productoRepository.findByNombreContainingIgnoreCase(search.trim(), pageable);
    }

    public Optional<Producto> obtenerProductoPorId(Long idProducto) {
        return productoRepository.findById(idProducto);
    }

    public Producto crearProducto(ProductoRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada"));

        Producto entidad = new Producto();
        entidad.setNombre(dto.getNombre());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setPrecio(dto.getPrecio());
        entidad.setCategoria(categoria);

        return productoRepository.save(entidad);
    }

    public Producto actualizarProducto(Long idProducto, Producto producto) {
        if (productoRepository.existsById(idProducto)) {
            producto.setIdProducto(idProducto);
            return productoRepository.save(producto);
        }
        return null;
    }

    public boolean eliminarProducto(Long idProducto) {
        if (productoRepository.existsById(idProducto)) {
            productoRepository.deleteById(idProducto);
            return true;
        }
        return false;
    }
}
