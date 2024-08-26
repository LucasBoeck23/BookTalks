package br.com.booktalks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.booktalks.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro, Integer>{

	  @Query("SELECT l FROM Livro l WHERE l.autor.id = :idPessoa")
	  List<Livro> findAlLivrosByPessoaId(@Param("idPessoa") Integer idPessoa);
}
