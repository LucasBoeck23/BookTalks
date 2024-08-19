package br.com.booktalks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.booktalks.entities.Comentario;
import br.com.booktalks.entities.Republicado;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer>{
	
	 @Query("SELECT r FROM Comentario r WHERE r.pessoa.id = :pessoaId")
	 List<Comentario> findComentariosByPessoaId(@Param("pessoaId") Integer pessoaId);

}
