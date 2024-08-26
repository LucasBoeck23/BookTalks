package br.com.booktalks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.booktalks.entities.Pessoa;
import br.com.booktalks.entities.Seguidor;

public interface SeguidorRepository extends JpaRepository<Seguidor, Integer>{

	 @Query("SELECT s FROM Seguidor s WHERE s.seguindo.id = :pessoaSeguidaId")
	 List<Seguidor> findAllSeguindoByPessoaId(@Param("pessoaSeguidaId") Integer pessoaSeguidaId);
	 
	 @Query("SELECT s FROM Seguidor s WHERE s.pessoa.id = :pessoaId")
	 List<Seguidor> findAllSeguidoresByPessoaId(@Param("pessoaId") Integer pessoaId);
}
