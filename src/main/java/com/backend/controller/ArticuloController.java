package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.backend.entity.Articulo;
import com.backend.response.ApiResponse;
import com.backend.service.ArticuloService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/articulo")
public class ArticuloController {

    @Autowired
    private ArticuloService articuloService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> guardar(@Valid @RequestBody Articulo request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            String errorMsg = bindingResult.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(new ApiResponse<>(errorMsg, HttpStatus.BAD_REQUEST.value(), null));
        }

        try {
            Articulo creado = articuloService.guardar(request);
            return ResponseEntity.ok(new ApiResponse<>("Artículo creado correctamente", HttpStatus.OK.value(), creado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

    @GetMapping
    public List<Articulo> listar() {
        return articuloService.listar();
    }

    @GetMapping("/{id}")
    public Articulo listarPorId(@PathVariable Integer id) {
        return articuloService.get(id);
    }

    @PutMapping("/{id}")
    public Articulo actualizar(@PathVariable Integer id, @RequestBody Articulo articulo) {
        return articuloService.update(id, articulo);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        articuloService.eliminar(id);
    }
}
