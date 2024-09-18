package br.com.booktalks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.booktalks.entities.ItemCarrinho;

public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Integer> {
	
	@Query("SELECT ic FROM ItemCarrinho ic WHERE ic.carrinho.id = :carrinhoId")
	List<ItemCarrinho> findItemCarrinhoByCarrinhoId (@Param("carrinhoId") Integer carrinhoId);
}
