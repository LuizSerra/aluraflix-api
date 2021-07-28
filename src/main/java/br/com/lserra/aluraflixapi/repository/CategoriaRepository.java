package br.com.lserra.aluraflixapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lserra.aluraflixapi.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
