package br.com.booktalks.dto;

public class CarrinhoDto {
	
	Integer carrinho_id;
	PessoaDto pessoa;
	LivroDto livro;
	int quantidade;
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
	public LivroDto getLivro() {
		return livro;
	}
	public void setLivro(LivroDto livro) {
		this.livro = livro;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	
	
}
