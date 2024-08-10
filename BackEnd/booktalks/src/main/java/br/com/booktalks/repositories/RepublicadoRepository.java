package br.com.booktalks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.booktalks.entities.Republicado;

public interface RepublicadoRepository extends JpaRepository<Republicado, Integer>{
	
	 @Query("SELECT r FROM Republicado r WHERE r.pessoa.id = :pessoaId")
	 List<Republicado> findRepublicacoesByPessoaId(@Param("pessoaId") Integer pessoaId);

}
