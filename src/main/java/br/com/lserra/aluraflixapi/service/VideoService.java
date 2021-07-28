package br.com.lserra.aluraflixapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.lserra.aluraflixapi.model.Video;
import br.com.lserra.aluraflixapi.repository.VideoRepository;

@Service
public class VideoService {

	@Autowired
	VideoRepository videoRepository;

	public Page<Video> listar(Pageable pagination) {
		return videoRepository.findAll(pagination);
	}

	public Optional<Video> getVideoById(Long id) {
		return videoRepository.findById(id);

	}

}
