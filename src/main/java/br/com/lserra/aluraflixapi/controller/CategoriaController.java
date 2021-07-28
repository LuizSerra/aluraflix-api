package br.com.lserra.aluraflixapi.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.lserra.aluraflixapi.controller.dto.CategoriaDTO;
import br.com.lserra.aluraflixapi.model.Categoria;
import br.com.lserra.aluraflixapi.repository.CategoriaRepository;
import br.com.lserra.aluraflixapi.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<Page<CategoriaDTO>> listar(Pageable pagination) {
		Page<CategoriaDTO> categoriaDTOList = CategoriaDTO.convertToCategoriaDTOList(categoriaService.listar(pagination));
		return !categoriaDTOList.isEmpty() ? ResponseEntity.ok(categoriaDTOList) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getVideoById(@PathVariable Long id) {
		Optional<Categoria> categoriaEncontrada = categoriaService.getCategoriaById(id);
		return categoriaEncontrada.isPresent() ? ResponseEntity.ok(new CategoriaDTO(categoriaEncontrada.get())) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");  
	}
	
	@PostMapping
	public ResponseEntity<CategoriaDTO> criar(@RequestBody @Validated Categoria categoria, UriComponentsBuilder uriBuilder) {
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoriaSalva.getId()).toUri();
		return ResponseEntity.created(uri).body(new CategoriaDTO(categoriaSalva));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO) {
		Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
		Categoria categoria;
		if(categoriaOptional.isPresent()) {
			categoria = categoriaOptional.get();
			if(categoriaDTO.getTitulo() != null) categoria.setTitulo(categoriaDTO.getTitulo());
			if(categoriaDTO.getCor() != null) categoria.setCor(categoriaDTO.getCor());
			return ResponseEntity.ok(new CategoriaDTO(categoriaOptional.get()));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");  
				
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
		if(categoriaOptional.isPresent()) {
			categoriaRepository.delete(categoriaOptional.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");  
	}
	
	
}
