package br.com.lserra.aluraflixapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lserra.aluraflixapi.model.Video;
import br.com.lserra.aluraflixapi.repository.VideoRepository;

@Service
public class VideoService {

	@Autowired
	VideoRepository videoRepository;

	public List<Video> listar() {
		return videoRepository.findAll();
	}

	public Optional<Video> getVideoById(Long id) {
		return videoRepository.findById(id);
		
	}

	public Video criar(Video video) {
		return videoRepository.save(video);
		
	}
	
	

}
