package com.backend.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.backend.entity.Cliente;
import com.backend.service.ClienteService;
import com.backend.response.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@PostMapping
	public ResponseEntity<ApiResponse<?>> guardar(@Valid @RequestBody Cliente request, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			String errorMsg = bindingResult.getFieldError().getDefaultMessage();
			return ResponseEntity.badRequest().body(new ApiResponse<>(errorMsg, HttpStatus.BAD_REQUEST.value(), null));
		}
		try {
			Cliente creado = clienteService.guardar(request);
			return ResponseEntity.badRequest().body(new ApiResponse<>("Cliente creado correctamente", HttpStatus.OK.value(), creado));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ApiResponse<>(e.getMessage(), HttpStatus.NOT_FOUND.value(), null));

		}

	}

	@GetMapping
	public List<Cliente> listar() {
		return clienteService.listar();
	}

	
	@GetMapping("/{id}")
	public Cliente listarPorId(@PathVariable Integer id) {
		return clienteService.get(id);
	}

	@PutMapping("/{id}")
	public Cliente actualizar(@PathVariable Integer id, @RequestBody Cliente cliente) {
		return clienteService.update(id, cliente);
	}
	
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Integer id) {
		clienteService.eliminar(id);
	}

}

