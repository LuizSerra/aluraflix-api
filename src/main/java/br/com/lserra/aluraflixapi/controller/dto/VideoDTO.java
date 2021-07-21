package br.com.lserra.aluraflixapi.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.lserra.aluraflixapi.model.Video;

public class VideoDTO {
	private Long id;
	private String titulo;
	private String descricao;
	private String url;
	
	public VideoDTO() {}
	public VideoDTO(Video video) {
		this.id = video.getId();
		this.titulo = video.getTitulo();
		this.descricao = video.getDescricao();
		this.url = video.getUrl();
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
	
	public static List<VideoDTO> convertToVideoDTOList(List<Video> list) {
		return list.stream().map(VideoDTO::new).collect(Collectors.toList());
	}
				
}
