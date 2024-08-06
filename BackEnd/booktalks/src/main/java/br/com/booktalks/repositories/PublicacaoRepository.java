package br.com.booktalks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.booktalks.entities.Publicacao;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Integer> {

}
