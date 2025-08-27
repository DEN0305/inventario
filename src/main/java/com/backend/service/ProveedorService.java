package com.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.Proveedor;
import com.backend.repository.ProveedorRepository;

@Service
public class ProveedorService {

	@Autowired
	private ProveedorRepository proveedorRepository;

	public Proveedor guardar(Proveedor proveedor) {
		return proveedorRepository.save(proveedor);
	}

	public List<Proveedor> listar() {
		return proveedorRepository.findAll();
	}

	public Proveedor get(Integer id) {
		return proveedorRepository.findById(id).orElse(null);
	}

	public Proveedor update(Integer id, Proveedor request) {

		Optional<Proveedor> p = proveedorRepository.findById(id);

		if (p.isPresent()) {
			Proveedor nuevo = p.get();

			nuevo.setNombre(request.getNombre());
			nuevo.setDireccion(request.getDireccion());
			nuevo.setTelefono(request.getTelefono());
			nuevo.setCorreo(request.getCorreo());

			return proveedorRepository.save(nuevo);

		} else {
			return null;
		}
	}

	public boolean eliminar(Integer id) {
		if (proveedorRepository.existsById(id)) {
			proveedorRepository.deleteById(id);
			return true;
		}
		return false;
	}

}

