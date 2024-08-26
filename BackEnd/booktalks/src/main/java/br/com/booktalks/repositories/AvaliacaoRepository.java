package br.com.booktalks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.booktalks.entities.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer>{

}
