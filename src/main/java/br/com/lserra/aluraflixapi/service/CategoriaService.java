package br.com.lserra.aluraflixapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.lserra.aluraflixapi.model.Categoria;
import br.com.lserra.aluraflixapi.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;

	public Page<Categoria> listar(Pageable pagination) {
		return categoriaRepository.findAll(pagination);
	}

	public Optional<Categoria> getCategoriaById(Long id) {
		return categoriaRepository.findById(id);
		
	}

}
