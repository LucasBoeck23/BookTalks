package br.com.booktalks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.booktalks.entities.Favorito;

public interface FavoritoRepository extends JpaRepository<Favorito, Integer>{

	@Query("SELECT f FROM Favorito f WHERE f.pessoa.id = :pessoaId")
	List<Favorito> findAllFavoritosByPessoaId(@Param ("pessoaId") Integer pessoaId ); 
	
}
