package br.com.booktalks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.booktalks.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro, Integer>{

}
