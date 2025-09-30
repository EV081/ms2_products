package com.example.ms2_productos.service;

import com.example.ms2_productos.domain.dto.CategoriaDTO;
import com.example.ms2_productos.domain.dto.ProductoResponseDTO;
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

    public Page<Producto> obtenerProductos(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }

    public Optional<Producto> obtenerProductoPorId(Long idProducto) {
        return productoRepository.findById(idProducto);
    }

    public Producto crearProducto(Producto producto) {
        Categoria categoria = categoriaRepository.findById(producto.getCategoria().getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        producto.setCategoria(categoria);

        return productoRepository.save(producto);
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
