package br.com.booktalks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.booktalks.entities.Citacao;

public interface CitacaoRepository extends JpaRepository<Citacao, Integer>{

}
