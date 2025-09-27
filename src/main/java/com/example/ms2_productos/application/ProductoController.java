package com.example.ms2_productos.application;

import com.example.ms2_productos.domain.Producto;
import com.example.ms2_productos.domain.ProductoResponseDTO;
import com.example.ms2_productos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Endpoint para obtener todos los productos con sus categorías
    @GetMapping
    public List<ProductoResponseDTO> obtenerTodosLosProductos() {
        return productoService.obtenerTodosLosProductos().stream()
                .map(productoService::convertirAProductoResponseDTO)
                .collect(Collectors.toList());
    }

    // Endpoint para obtener un producto por ID con su categoría
    @GetMapping("/{idProducto}")
    public ResponseEntity<ProductoResponseDTO> obtenerProductoPorId(@PathVariable Long idProducto) {
        Optional<Producto> producto = productoService.obtenerProductoPorId(idProducto);
        return producto.map(p -> ResponseEntity.ok(productoService.convertirAProductoResponseDTO(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para crear un producto
    @PostMapping
    public ProductoResponseDTO crearProducto(@RequestBody Producto producto) {
        Producto productoCreado = productoService.crearProducto(producto);
        return productoService.convertirAProductoResponseDTO(productoCreado);
    }

    // Endpoint para actualizar un producto
    @PutMapping("/{idProducto}")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(@PathVariable Long idProducto, @RequestBody Producto producto) {
        Producto productoActualizado = productoService.actualizarProducto(idProducto, producto);
        return productoActualizado != null
                ? ResponseEntity.ok(productoService.convertirAProductoResponseDTO(productoActualizado))
                : ResponseEntity.notFound().build();
    }

    // Endpoint para eliminar un producto
    @DeleteMapping("/{idProducto}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long idProducto) {
        return productoService.eliminarProducto(idProducto)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}
