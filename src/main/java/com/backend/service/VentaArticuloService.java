package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.Articulo;
import com.backend.entity.Venta;
import com.backend.entity.VentaArticulo;

import com.backend.repository.ArticuloRepository;
import com.backend.repository.VentaArticuloRepository;
import com.backend.repository.VentaRepository;

@Service
public class VentaArticuloService {

	@Autowired
	private VentaArticuloRepository ventaArticuloRepository;

	@Autowired
	private VentaRepository ventaRepository;

	@Autowired
	private ArticuloRepository articuloRepository;

	public VentaArticulo guardar(VentaArticulo request) {

		Venta idVenta = ventaRepository.findById(request.getVenta().getId())
				.orElseThrow(() -> new RuntimeException("Venta general no encontrada"));

		Articulo idArticulo = articuloRepository.findById(request.getArticulo().getId())
				.orElseThrow(() -> new RuntimeException("Articulo no encontrado"));

		request.setVenta(idVenta);
		request.setArticulo(idArticulo);

		return ventaArticuloRepository.save(request);
	}

	public List<VentaArticulo> listar() {
		return ventaArticuloRepository.findAll();
	}

	public VentaArticulo get(Integer id) {
		return ventaArticuloRepository.findById(id).orElse(null);
	}

	public VentaArticulo update(Integer id, VentaArticulo request) {

		VentaArticulo ventaArticulo = ventaArticuloRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Venta de articulo no encontrada"));

		Venta idVenta = ventaRepository.findById(request.getVenta().getId())
				.orElseThrow(() -> new RuntimeException("Venta general no encontrada"));

		Articulo idArticulo = articuloRepository.findById(request.getArticulo().getId())
				.orElseThrow(() -> new RuntimeException("Articulo no encontrado"));

		ventaArticulo.setVenta(idVenta);
		ventaArticulo.setArticulo(idArticulo);
		ventaArticulo.setCantidad(request.getCantidad());
		ventaArticulo.setValor(request.getValor());

		return ventaArticuloRepository.save(ventaArticulo);
	}

	public boolean eliminar(Integer id) {
		if (ventaArticuloRepository.existsById(id)) {
			ventaArticuloRepository.deleteById(id);
			return true;
		}
		return false;
	}

}