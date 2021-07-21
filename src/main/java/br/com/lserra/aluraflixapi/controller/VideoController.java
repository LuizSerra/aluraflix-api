package br.com.lserra.aluraflixapi.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.lserra.aluraflixapi.controller.dto.VideoDTO;
import br.com.lserra.aluraflixapi.model.Video;
import br.com.lserra.aluraflixapi.service.VideoService;

@RestController
@RequestMapping("/videos")
public class VideoController {

	@Autowired
	private VideoService videoService;
	
	@GetMapping
	public ResponseEntity<List<VideoDTO>> listar() {
		List<VideoDTO> videoDTOList = VideoDTO.convertToVideoDTOList(videoService.listar());
		return !videoDTOList.isEmpty() ? ResponseEntity.ok(videoDTOList) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getVideoById(@PathVariable("id") @Validated Long id) {
		Optional<Video> video = videoService.getVideoById(id);
		return video.isPresent() ? ResponseEntity.ok(new VideoDTO(video.get())) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");  
	}
	
	@PostMapping
	public ResponseEntity<VideoDTO> criar(@RequestBody @Validated Video video, UriComponentsBuilder uriBuilder) {
		Video videoSalvo = videoService.criar(video);
		URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(videoSalvo.getId()).toUri();
		return ResponseEntity.created(uri).body(new VideoDTO(videoSalvo));
	}
	
}
