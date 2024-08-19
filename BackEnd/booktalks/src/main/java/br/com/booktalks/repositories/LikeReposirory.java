package br.com.booktalks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.booktalks.entities.Like;
import br.com.booktalks.entities.Pessoa;
import br.com.booktalks.entities.Publicacao;

public interface LikeReposirory extends JpaRepository<Like, Integer>{

	  @Query("SELECT l.publicacao FROM Like l WHERE l.pessoa.id = :idPessoa")
	  List<Publicacao> findPublicacoesByPessoaId(@Param("idPessoa") Integer idPessoa);
	  
	  @Query("SELECT l.pessoa FROM Like l WHERE l.publicacao.id = :idPublicacao")
	  List<Pessoa>findPessoasByPublicacaoId(@Param("idPublicacao") Integer idPublicacao);
	  
	  @Query("SELECT l FROM Like l WHERE l.pessoa.id = :idPessoa")
	  List<Like>findAllLikesByPessoaId(@Param("idPessoa") Integer idPessoa);
}
