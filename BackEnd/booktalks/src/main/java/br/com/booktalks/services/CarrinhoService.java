package br.com.booktalks.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.booktalks.dto.CarrinhoDto;
import br.com.booktalks.entities.Carrinho;
import br.com.booktalks.entities.Livro;
import br.com.booktalks.entities.Pessoa;
import br.com.booktalks.repositories.CarrinhoRepository;
import br.com.booktalks.repositories.LivroRepository;
import br.com.booktalks.repositories.PessoaRepository;

@Service
public class CarrinhoService {

	@Autowired
	CarrinhoRepository carrinhoRepository;
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@Autowired
	LivroRepository livroRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public CarrinhoDto adicionar (Integer pessoaId, Integer livroId) {
		Pessoa pessoa = pessoaRepository.findById(pessoaId).orElse(null);
		Livro livro = livroRepository.findById(livroId).orElse(null);
		Carrinho carrinho = new Carrinho();		
		if(livro != null && pessoa != null) {
			
			
			return modelMapper.map(carrinho, CarrinhoDto.class);
		}
		return null;
	}
}
