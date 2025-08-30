package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.Articulo;
import com.backend.entity.Compra;
import com.backend.entity.CompraArticulo;
import com.backend.repository.ArticuloRepository;
import com.backend.repository.CompraArticuloRepository;
import com.backend.repository.CompraRepository;

@Service
public class CompraArticuloService {

	@Autowired
	private CompraArticuloRepository compraArticuloRepository;

	@Autowired
	private ArticuloRepository articuloRepository;

	@Autowired
	private CompraRepository compraRepository;

	public CompraArticulo guardar(CompraArticulo request) {

		Articulo idArticulo = articuloRepository.findById(request.getArticulo().getId())
				.orElseThrow(() -> new RuntimeException("Articulo no encontrado"));

		Compra idCompra = compraRepository.findById(request.getCompra().getId())
				.orElseThrow(() -> new RuntimeException("Compra no encontrada"));

		request.setArticulo(idArticulo);
		request.setCompra(idCompra);

		return compraArticuloRepository.save(request);
	}

	public List<CompraArticulo> listar() {
		return compraArticuloRepository.findAll();
	}

	public CompraArticulo get(Integer id) {
		return compraArticuloRepository.findById(id).orElse(null);
	}

	public CompraArticulo update(Integer id, CompraArticulo request) {

		CompraArticulo compraArticulo = compraArticuloRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Compra de articulo no encontrada"));

		Articulo idArticulo = articuloRepository.findById(request.getArticulo().getId())
				.orElseThrow(() -> new RuntimeException("Articulo no encontrado"));

		Compra idCompra = compraRepository.findById(request.getCompra().getId())
				.orElseThrow(() -> new RuntimeException("Compra no encontrada"));

		compraArticulo.setArticulo(idArticulo);
		compraArticulo.setCompra(idCompra);
		compraArticulo.setValor(request.getValor());
		compraArticulo.setCantidad(request.getCantidad());
		compraArticulo.setValor_unitario(request.getValor_unitario());

		return compraArticuloRepository.save(compraArticulo);

	}

	public boolean eliminar(Integer id) {
		if (compraArticuloRepository.existsById(id)) {
			compraArticuloRepository.deleteById(id);
			return true;
		}
		return false;
	}

}