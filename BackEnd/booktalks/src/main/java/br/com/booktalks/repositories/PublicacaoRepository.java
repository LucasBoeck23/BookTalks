package br.com.booktalks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.booktalks.entities.Publicacao;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Integer> {
	
	 @Query("SELECT p FROM Publicacao p WHERE p.pessoa.id = :idPessoa")
	 List<Publicacao> findAllPublicacoesByPessoaId(@Param("idPessoa") Integer idPessoa);

}
