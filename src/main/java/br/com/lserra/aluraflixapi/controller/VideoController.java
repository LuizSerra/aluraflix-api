package br.com.lserra.aluraflixapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lserra.aluraflixapi.controller.dto.VideoDTO;
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
	public ResponseEntity<VideoDTO> getVideoById(@PathVariable("id") Long id) {
		VideoDTO videoDTO = new VideoDTO(videoService.getVideoById(id));
		return videoDTO != null ? ResponseEntity.ok(videoDTO) : ResponseEntity.noContent().build(); 
	}
	
}
