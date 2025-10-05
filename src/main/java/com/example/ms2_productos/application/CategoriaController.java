package com.example.ms2_productos.application;

import com.example.ms2_productos.domain.Categoria;
import com.example.ms2_productos.domain.Producto;
import com.example.ms2_productos.domain.dto.PaginatedResponse;
import com.example.ms2_productos.domain.dto.ProductoResponseDTO;
import com.example.ms2_productos.service.CategoriaService;
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
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public PaginatedResponse<Categoria> obtenerCategorias(
            @PageableDefault(page = 0, size = 10) Pageable pageable,
            @RequestParam(value = "search", required = false) String search
    ) {
        Page<Categoria> page = categoriaService.obtenerCategorias(pageable, search);

        PaginatedResponse<Categoria> resp = new PaginatedResponse<>();
        resp.setContents(page.getContent());
        resp.setPage(page.getNumber());
        resp.setSize(page.getSize());
        resp.setTotalElements(page.getTotalElements());
        resp.setTotalPages(page.getTotalPages());
        return resp;
    }

    @GetMapping("/{idCategoria}/productos")
    public PaginatedResponse<ProductoResponseDTO> obtenerProductosPorCategoria(
            @PathVariable Long idCategoria,
            @PageableDefault(page = 0, size = 10) Pageable pageable,
            @RequestParam(value = "search", required = false) String search
    ) {
        Page<Producto> page = categoriaService.obtenerProductosPorCategoria(idCategoria, pageable, search);

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

    @GetMapping("/{idCategoria}")
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable Long idCategoria) {
        Optional<Categoria> categoria = categoriaService.obtenerCategoriaPorId(idCategoria);
        return categoria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Categoria crearCategoria(@RequestBody Categoria categoria) {
        return categoriaService.crearCategoria(categoria);
    }

    @PutMapping("/{idCategoria}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long idCategoria, @RequestBody Categoria categoria) {
        Categoria categoriaActualizada = categoriaService.actualizarCategoria(idCategoria, categoria);
        return categoriaActualizada != null ? ResponseEntity.ok(categoriaActualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idCategoria}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long idCategoria) {
        return categoriaService.eliminarCategoria(idCategoria) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
