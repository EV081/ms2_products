package com.example.ms2_productos.application;

import com.example.ms2_productos.domain.Producto;
import com.example.ms2_productos.domain.dto.PaginatedResponse;
import com.example.ms2_productos.domain.dto.ProductoResponseDTO;
import com.example.ms2_productos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public PaginatedResponse<ProductoResponseDTO> obtenerProductos(
            @PageableDefault(page = 0, size = 10) Pageable pageable,
            @RequestParam(value = "search", required = false) String search
    ) {
        Page<Producto> page = productoService.obtenerProductos(pageable, search);

        List<ProductoResponseDTO> content = page.getContent().stream()
                .map(productoService::convertirAProductoResponseDTO)
                .toList();

        PaginatedResponse<ProductoResponseDTO> resp = new PaginatedResponse<>();
        resp.setContents(content);
        resp.setPage(page.getNumber());
        resp.setSize(page.getSize());
        resp.setTotalElements(page.getTotalElements());
        resp.setTotalPages(page.getTotalPages());
        return resp;
    }

    @GetMapping("/{idProducto}")
    public ResponseEntity<ProductoResponseDTO> obtenerProductoPorId(@PathVariable Long idProducto) {
        Optional<Producto> producto = productoService.obtenerProductoPorId(idProducto);
        return producto.map(p -> ResponseEntity.ok(productoService.convertirAProductoResponseDTO(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProductoResponseDTO crearProducto(@RequestBody Producto producto) {
        Producto productoCreado = productoService.crearProducto(producto);
        return productoService.convertirAProductoResponseDTO(productoCreado);
    }

    @PutMapping("/{idProducto}")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(@PathVariable Long idProducto, @RequestBody Producto producto) {
        Producto productoActualizado = productoService.actualizarProducto(idProducto, producto);
        return productoActualizado != null
                ? ResponseEntity.ok(productoService.convertirAProductoResponseDTO(productoActualizado))
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idProducto}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long idProducto) {
        return productoService.eliminarProducto(idProducto)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}
