package br.com.booktalks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.booktalks.entities.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer>{

	 @Query("SELECT a FROM Avaliacao a WHERE a.pessoa.id = :pessoaId")
	 List<Avaliacao> findAllAvaliacoesByPessoaId(@Param("pessoaId") Integer pessoaId);
	 
	 @Query("SELECT a FROM Avaliacao a WHERE a.livro.id = :livroId")
	 List<Avaliacao> findAllAvaliacoesByLivroId(@Param("livroId") Integer livroId);
	 
}
