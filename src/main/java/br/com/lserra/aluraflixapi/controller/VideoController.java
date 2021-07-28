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

import br.com.lserra.aluraflixapi.controller.dto.VideoDTO;
import br.com.lserra.aluraflixapi.model.Video;
import br.com.lserra.aluraflixapi.repository.VideoRepository;
import br.com.lserra.aluraflixapi.service.VideoService;

@RestController
@RequestMapping("/videos")
public class VideoController {

	@Autowired
	private VideoService videoService;
	@Autowired
	VideoRepository videoRepository;
	
	@GetMapping
	public ResponseEntity<Page<VideoDTO>> listar(Pageable pagination) {
		Page<VideoDTO> videoDTOList = VideoDTO.convertToVideoDTOList(videoService.listar(pagination));
		return !videoDTOList.isEmpty() ? ResponseEntity.ok(videoDTOList) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getVideoById(@PathVariable Long id) {
		Optional<Video> video = videoService.getVideoById(id);
		return video.isPresent() ? ResponseEntity.ok(new VideoDTO(video.get())) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");  
	}
	
	@PostMapping
	public ResponseEntity<VideoDTO> criar(@RequestBody @Validated Video video, UriComponentsBuilder uriBuilder) {
		Video videoSalvo = videoRepository.save(video);
		URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(videoSalvo.getId()).toUri();
		return ResponseEntity.created(uri).body(new VideoDTO(videoSalvo));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody VideoDTO videoDTO) {
		Optional<Video> videoOptional = videoRepository.findById(id);
		Video video;
		if(videoOptional.isPresent()) {
		    video = videoOptional.get();
			if(videoDTO.getTitulo() != null) video.setTitulo(videoDTO.getTitulo());
			if(videoDTO.getDescricao() != null) video.setDescricao(videoDTO.getDescricao());
			if(videoDTO.getUrl() != null) video.setUrl(videoDTO.getUrl());
			return ResponseEntity.ok(new VideoDTO(videoOptional.get()));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");  
				
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Video> videoOptional = videoRepository.findById(id);
		if(videoOptional.isPresent()) {
			videoRepository.delete(videoOptional.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");  
	}
	
	
}
