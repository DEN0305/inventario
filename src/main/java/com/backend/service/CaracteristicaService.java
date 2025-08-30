package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.Caracteristica;
import com.backend.repository.CaracteristicaRepository;

@Service
public class CaracteristicaService {

	@Autowired
	private CaracteristicaRepository caracteristicaRepository;

	public Caracteristica guardar(Caracteristica caracteristica) {
		return caracteristicaRepository.save(caracteristica);
	}

	public List<Caracteristica> listar() {
		return caracteristicaRepository.findAll();
	}

	public Caracteristica get(Integer id) {
		return caracteristicaRepository.findById(id).orElse(null);
	}

	public Caracteristica update(Integer id, Caracteristica request) {

		Caracteristica caracteristica = caracteristicaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Caracteristica no encontrada"));

		caracteristica.setDescripcion(request.getDescripcion());

		return caracteristicaRepository.save(caracteristica);
	}

	public boolean eliminar(Integer id) {
		if (caracteristicaRepository.existsById(id)) {
			caracteristicaRepository.deleteById(id);
			return true;
		}
		return false;
	}

}