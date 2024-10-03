package br.com.booktalks.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.booktalks.dto.CarrinhoDto;
import br.com.booktalks.dto.ItemCarrinhoDto;
import br.com.booktalks.dto.LivroDto;
import br.com.booktalks.entities.Carrinho;
import br.com.booktalks.entities.ItemCarrinho;
import br.com.booktalks.entities.Livro;
import br.com.booktalks.entities.Pessoa;
import br.com.booktalks.repositories.CarrinhoRepository;
import br.com.booktalks.repositories.ItemCarrinhoRepository;
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
	ItemCarrinhoRepository itemCarrinhoRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public CarrinhoDto adicionar (Integer pessoaId, Integer livroId, int quantidade) {
		Pessoa pessoa = pessoaRepository.findById(pessoaId).orElse(null);
		Livro livro = livroRepository.findById(livroId).orElse(null);
		if(livro == null) {
			throw new IllegalArgumentException("Livro não existente");
		}
		if(pessoa == null){
			throw new IllegalArgumentException("Pessoa não existente");
		}
		Carrinho carrinho = carrinhoRepository.findById(pessoa.getCarrinho().getCarrinho_id()).orElse(null);
		if(livro != null && pessoa != null) {
			ItemCarrinho itemCarrinho = new ItemCarrinho();
			itemCarrinho.setCarrinho(carrinho);
			itemCarrinho.setLivro(livro);
			itemCarrinho.setQuantidade(quantidade);
			itemCarrinhoRepository.save(itemCarrinho);
			
			carrinho.getItens().add(itemCarrinho);
			carrinho.setQuantidadeItens(carrinho.getQuantidadeItens()+1);
			
			carrinhoRepository.save(carrinho);
			return modelMapper.map(carrinho, CarrinhoDto.class);
		}
		return null;
	}
	
	public CarrinhoDto findCarrinhoByPessoaId(Integer pessoaId){
		Pessoa pessoa = pessoaRepository.findById(pessoaId).orElse(null);
		if(pessoa == null){
			throw new IllegalArgumentException("Pessoa não existente");
		}
		Carrinho carrinho = carrinhoRepository.findById(pessoa.getCarrinho().getCarrinho_id()).orElse(null);
		List<ItemCarrinho> itemCarrinho = itemCarrinhoRepository.findItemCarrinhoByCarrinhoId(pessoa.getCarrinho().getCarrinho_id());
		List<ItemCarrinhoDto> itemCarrinhoDto = new ArrayList<>();
		for (ItemCarrinho itemCarrinhoLista : itemCarrinho) {
			ItemCarrinhoDto itemCarrinhoDtoLista = modelMapper.map(itemCarrinhoLista, ItemCarrinhoDto.class);
			itemCarrinhoDtoLista.setLivroDto(modelMapper.map(itemCarrinhoLista.getLivro() ,LivroDto.class));
			itemCarrinhoDto.add(itemCarrinhoDtoLista);
		}
		CarrinhoDto	carrinhoDto = modelMapper.map(carrinho, CarrinhoDto.class);
		carrinhoDto.setItens(itemCarrinhoDto);
		return carrinhoDto;
	}
	
	public ItemCarrinhoDto remover(Integer ItemProdutoId){
		ItemCarrinho itemCarrinho = itemCarrinhoRepository.findById(ItemProdutoId).orElse(null);
		if(itemCarrinho != null){
			itemCarrinhoRepository.deleteById(ItemProdutoId);
		} else if (itemCarrinho == null){
			throw new IllegalArgumentException("Item não existente");
		}
		ItemCarrinhoDto itemCarrinhoDto = modelMapper.map(itemCarrinho, ItemCarrinhoDto.class);
		itemCarrinhoDto.setLivroDto(modelMapper.map(itemCarrinho.getLivro(), LivroDto.class));
		return itemCarrinhoDto;
		
	}
}
