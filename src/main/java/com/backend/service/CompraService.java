package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.Compra;
import com.backend.entity.Proveedor;

import com.backend.repository.CompraRepository;
import com.backend.repository.ProveedorRepository;

@Service
public class CompraService {

	@Autowired
	private CompraRepository compraRepository;

	@Autowired
	private ProveedorRepository proveedorRepository;

	public Compra guardar(Compra request) {

		Proveedor idProveedor = proveedorRepository.findById(request.getProveedor().getId())
				.orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

		request.setProveedor(idProveedor);

		return compraRepository.save(request);
	}

	public List<Compra> listar() {
		return compraRepository.findAll();
	}

	public Compra get(Integer id) {
		return compraRepository.findById(id).orElse(null);
	}

	public Compra update(Integer id, Compra request) {

		Compra compra = compraRepository.findById(id).orElseThrow(() -> new RuntimeException("Compra no encontrada"));

		Proveedor idProveedor = proveedorRepository.findById(request.getProveedor().getId())
				.orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

		compra.setFecha(request.getFecha());
		compra.setProveedor(idProveedor);
		compra.setValor(request.getValor());

		return compraRepository.save(compra);
	}

}
