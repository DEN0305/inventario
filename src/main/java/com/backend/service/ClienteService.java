package com.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.Cliente;
import com.backend.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente guardar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}

	public Cliente get(Integer id) {
		return clienteRepository.findById(id).orElse(null);
	}

	public Cliente update(Integer id, Cliente request) {

		Optional<Cliente> p = clienteRepository.findById(id);

		if (p.isPresent()) {
			Cliente nuevo = p.get();

			nuevo.setNombre(request.getNombre());
			nuevo.setDireccion(request.getDireccion());
			nuevo.setTelefono(request.getTelefono());
			nuevo.setCorreo(request.getCorreo());

			return clienteRepository.save(nuevo);

		} else {
			return null;
		}
	}

	public boolean eliminar(Integer id) {
		if (clienteRepository.existsById(id)) {
			clienteRepository.deleteById(id);
			return true;
		}
		return false;
	}

}

