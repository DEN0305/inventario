package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.Categoria;
import com.backend.repository.CategoriaRepository;

import com.backend.entity.Articulo;
import com.backend.repository.ArticuloRepository;

@Service
public class ArticuloService {

	@Autowired
	private ArticuloRepository articuloRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Articulo guardar(Articulo request) {

		Categoria idCategoria = categoriaRepository.findById(request.getCategoria().getId())
				.orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

		request.setCategoria(idCategoria);

		return articuloRepository.save(request);
	}

	public List<Articulo> listar() {
		return articuloRepository.findAll();
	}

	public Articulo get(Integer id) {
		return articuloRepository.findById(id).orElse(null);
	}

	public Articulo update(Integer id, Articulo request) {

		Articulo articulo = articuloRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Articulo no encontrado"));

		Categoria idCategoria = categoriaRepository.findById(request.getCategoria().getId())
				.orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

		articulo.setNombre(request.getNombre());
		articulo.setCategoria(idCategoria);

		return articuloRepository.save(articulo);

	}

	public boolean eliminar(Integer id) {
		if (articuloRepository.existsById(id)) {
			articuloRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
