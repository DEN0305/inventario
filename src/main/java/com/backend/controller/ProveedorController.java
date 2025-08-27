package com.backend.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.backend.entity.Proveedor;
import com.backend.service.ProveedorService;
import com.backend.response.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/proveedor")
public class ProveedorController {

	@Autowired
	private ProveedorService proveedorService;

	@PostMapping
	public ResponseEntity<ApiResponse<?>> guardar(@Valid @RequestBody Proveedor request, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			String errorMsg = bindingResult.getFieldError().getDefaultMessage();
			return ResponseEntity.badRequest().body(new ApiResponse<>(errorMsg, HttpStatus.BAD_REQUEST.value(), null));
		}
		try {
			Proveedor creado = proveedorService.guardar(request);
			return ResponseEntity.badRequest().body(new ApiResponse<>("Proveedor creado correctamente", HttpStatus.OK.value(), creado));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ApiResponse<>(e.getMessage(), HttpStatus.NOT_FOUND.value(), null));

		}

	}

	@GetMapping
	public List<Proveedor> listar() {
		return proveedorService.listar();
	}

	
	@GetMapping("/{id}")
	public Proveedor listarPorId(@PathVariable Integer id) {
		return proveedorService.get(id);
	}

	@PutMapping("/{id}")
	public Proveedor actualizar(@PathVariable Integer id, @RequestBody Proveedor proveedor) {
		return proveedorService.update(id, proveedor);
	}
	
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Integer id) {
		proveedorService.eliminar(id);
	}

}
