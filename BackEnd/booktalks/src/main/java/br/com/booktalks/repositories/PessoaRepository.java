package br.com.booktalks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.booktalks.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
