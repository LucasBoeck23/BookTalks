package br.com.booktalks.dto;

import java.util.List;

public class CarrinhoDto {
	
	Integer carrinho_id;
	PessoaDto pessoa;
	List<ItemCarrinhoDto> itens;
	int quantidadeItens;
	public Integer getCarrinho_id() {
		return carrinho_id;
	}
	public void setCarrinho_id(Integer carrinho_id) {
		this.carrinho_id = carrinho_id;
	}
	public PessoaDto getPessoa() {
		return pessoa;
	}
	public void setPessoa(PessoaDto pessoa) {
		this.pessoa = pessoa;
	}
	
	public int getQuantidadeItens() {
		return quantidadeItens;
	}
	public void setQuantidadeItens(int quantidadeItens) {
		this.quantidadeItens = quantidadeItens;
	}
	public List<ItemCarrinhoDto> getItens() {
		return itens;
	}
	public void setItens(List<ItemCarrinhoDto> itens) {
		this.itens = itens;
	}
	
	
	
	
}
