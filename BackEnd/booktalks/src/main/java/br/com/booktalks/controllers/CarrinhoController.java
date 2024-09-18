package br.com.booktalks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.booktalks.dto.CarrinhoDto;
import br.com.booktalks.services.CarrinhoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

	
	@Autowired
	CarrinhoService carrinhoService;
	
	@PostMapping("/adicionar/{pessoaId}/{livroId}/{quantidade}")
	public ResponseEntity<CarrinhoDto> adicionar (@PathVariable Integer pessoaId, @PathVariable Integer livroId, @PathVariable int quantidade){
		return new ResponseEntity<>(carrinhoService.adicionar(pessoaId, livroId, quantidade), HttpStatus.OK);
	}
	
	@GetMapping("/pessoa/{pessoaId}")
	public ResponseEntity<CarrinhoDto> findCarrinhoByPessoaId(@PathVariable Integer pessoaId) {
		return new ResponseEntity<>(carrinhoService.findCarrinhoByPessoaId(pessoaId), HttpStatus.OK);
	}
	
}
