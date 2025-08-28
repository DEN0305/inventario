package com.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.Articulo;
import com.backend.repository.ArticuloRepository;

@Service
public class ArticuloService {

    @Autowired
    private ArticuloRepository articuloRepository;

    public Articulo guardar(Articulo articulo) {
        return articuloRepository.save(articulo);
    }

    public List<Articulo> listar() {
        return articuloRepository.findAll();
    }

    public Articulo get(Integer id) {
        return articuloRepository.findById(id).orElse(null);
    }

    public Articulo update(Integer id, Articulo request) {
        Optional<Articulo> opt = articuloRepository.findById(id);

        if (opt.isPresent()) {
            Articulo actualizado = opt.get();

            actualizado.setNombre(request.getNombre());
            actualizado.setDescripcion(request.getDescripcion());
            actualizado.setCantidad(request.getCantidad());
            actualizado.setEstado(request.getEstado());
            actualizado.setCategoria(request.getCategoria());

            return articuloRepository.save(actualizado);
        } else {
            return null;
        }
    }

    public boolean eliminar(Integer id) {
        if (articuloRepository.existsById(id)) {
            articuloRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
