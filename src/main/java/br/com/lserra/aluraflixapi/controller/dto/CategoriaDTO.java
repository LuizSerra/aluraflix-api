package br.com.lserra.aluraflixapi.controller.dto;

import org.springframework.data.domain.Page;

import br.com.lserra.aluraflixapi.model.Categoria;

public class CategoriaDTO {
	private Long id;
	private String titulo;
	private String cor;
		
	public CategoriaDTO() {}
	public CategoriaDTO(Categoria categoria) {
		this.id = categoria.getId();
		this.titulo = categoria.getTitulo();
		this.cor = categoria.getCor();
	}
	
	public Long getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getCor() {
		return cor;
	}
	
	public static Page<CategoriaDTO> convertToCategoriaDTOList(Page<Categoria> categorias) {
		return categorias.map(CategoriaDTO::new);
	}
}
