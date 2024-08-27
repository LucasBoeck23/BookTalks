package br.com.booktalks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.booktalks.entities.Carrinho;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Integer> {

}
