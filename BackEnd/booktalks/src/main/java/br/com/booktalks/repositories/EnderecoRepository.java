package br.com.booktalks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.booktalks.entities.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco,Integer>{
	 Endereco findByCep(String cep);
}
