package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.Categoria;
import com.backend.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria guardar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}

	public Categoria get(Integer id) {
		return categoriaRepository.findById(id).orElse(null);
	}

	public Categoria update(Integer id, Categoria request) {

		Categoria categoria = categoriaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

		categoria.setDescripcion(request.getDescripcion());

		return categoriaRepository.save(categoria);
	}

	public boolean eliminar(Integer id) {
		if (categoriaRepository.existsById(id)) {
			categoriaRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
