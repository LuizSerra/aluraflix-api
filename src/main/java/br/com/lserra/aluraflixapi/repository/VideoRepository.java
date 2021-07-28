package br.com.lserra.aluraflixapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lserra.aluraflixapi.model.Video;

public interface VideoRepository extends JpaRepository<Video, Long>{

	Page<Video> findByTitulo(String search, Pageable pagination);

}
