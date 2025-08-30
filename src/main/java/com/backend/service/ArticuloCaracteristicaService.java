package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.Articulo;
import com.backend.entity.ArticuloCaracteristicas;
import com.backend.entity.Caracteristica;

import com.backend.repository.ArticuloCaracteristicasRepository;
import com.backend.repository.ArticuloRepository;
import com.backend.repository.CaracteristicaRepository;

@Service
public class ArticuloCaracteristicaService {

	@Autowired
	private ArticuloCaracteristicasRepository articuloCaracteristicasRepository;

	@Autowired
	private CaracteristicaRepository caracteristicaRepository;

	@Autowired
	private ArticuloRepository articuloRepository;

	public ArticuloCaracteristicas guardar(ArticuloCaracteristicas request) {

		Caracteristica idCaracteristica = caracteristicaRepository.findById(request.getCaracteristica().getId())
				.orElseThrow(() -> new RuntimeException("Caracteristica no encontrada"));

		Articulo idArticulo = articuloRepository.findById(request.getArticulo().getId())
				.orElseThrow(() -> new RuntimeException("Articulo no encontrado"));

		request.setArticulo(idArticulo);
		request.setCaracteristica(idCaracteristica);

		return articuloCaracteristicasRepository.save(request);

	}

	public List<ArticuloCaracteristicas> listar() {
		return articuloCaracteristicasRepository.findAll();
	}

	public ArticuloCaracteristicas get(Integer id) {
		return articuloCaracteristicasRepository.findById(id).orElse(null);
	}

	public ArticuloCaracteristicas update(Integer id, ArticuloCaracteristicas request) {

		ArticuloCaracteristicas articuloCaracteristicas = articuloCaracteristicasRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Caracteristica de articulo no encontrada"));

		Caracteristica idCaracteristica = caracteristicaRepository.findById(request.getCaracteristica().getId())
				.orElseThrow(() -> new RuntimeException("Caracteristica no encontrada"));

		Articulo idArticulo = articuloRepository.findById(request.getArticulo().getId())
				.orElseThrow(() -> new RuntimeException("Articulo no encontrado"));

		articuloCaracteristicas.setArticulo(idArticulo);
		articuloCaracteristicas.setCaracteristica(idCaracteristica);
		articuloCaracteristicas.setValor(request.getValor());

		return articuloCaracteristicasRepository.save(articuloCaracteristicas);

	}

	public boolean eliminar(Integer id) {
		if (articuloCaracteristicasRepository.existsById(id)) {
			articuloCaracteristicasRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
