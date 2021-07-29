package br.com.lserra.aluraflixapi.controller.dto;

import org.springframework.data.domain.Page;

import br.com.lserra.aluraflixapi.model.Categoria;
import br.com.lserra.aluraflixapi.model.Video;

public class VideoDTO {
	private Long id;
	private String titulo;
	private String descricao;
	private String url;
	private Categoria categoriaId;
	
	public VideoDTO() {}
	
	public VideoDTO(Video video) {
		this.id = video.getId();
		this.titulo = video.getTitulo();
		this.descricao = video.getDescricao();
		this.url = video.getUrl();
		this.categoriaId = video.getCategoria();
	}
	
	public Long getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public String getUrl() {
		return url;
	}
	public Categoria getCategoriaId() {
		return categoriaId;
	}
	public static Page<VideoDTO> convertToVideoDTOList(Page<Video> videos) {
		return videos.map(VideoDTO::new);
	}
				
}
