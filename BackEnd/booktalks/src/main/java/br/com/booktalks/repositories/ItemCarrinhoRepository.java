package br.com.booktalks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.booktalks.entities.ItemCarrinho;

public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Integer> {
}
