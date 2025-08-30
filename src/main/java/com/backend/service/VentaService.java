package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.Cliente;
import com.backend.repository.ClienteRepository;

import com.backend.entity.Venta;
import com.backend.repository.VentaRepository;

@Service
public class VentaService {

	@Autowired
	private VentaRepository ventaRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	public Venta guardar(Venta request) {

		Cliente idCliente = clienteRepository.findById(request.getCliente().getId())
				.orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

		request.setCliente(idCliente);

		return ventaRepository.save(request);
	}

	public List<Venta> listar() {
		return ventaRepository.findAll();
	}

	public Venta get(Integer id) {
		return ventaRepository.findById(id).orElse(null);
	}

	public Venta update(Integer id, Venta request) {

		Venta venta = ventaRepository.findById(id).orElseThrow(() -> new RuntimeException("Venta no encontrada"));

		Cliente idCliente = clienteRepository.findById(request.getCliente().getId())
				.orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

		venta.setFecha(request.getFecha());
		venta.setCliente(idCliente);
		venta.setValor(request.getValor());

		return ventaRepository.save(venta);

	}

	public boolean eliminar(Integer id) {
		if (ventaRepository.existsById(id)) {
			ventaRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
