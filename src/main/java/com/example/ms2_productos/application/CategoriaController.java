package com.example.ms2_productos.application;

import com.example.ms2_productos.domain.Categoria;
import com.example.ms2_productos.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> obtenerTodasLasCategorias() {
        return categoriaService.obtenerTodasLasCategorias();
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
