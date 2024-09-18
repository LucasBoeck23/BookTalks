package br.com.booktalks.dto;

public class ItemCarrinhoDto {

	private Integer id;
	private LivroDto livroDto;
	private int quantidade;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LivroDto getLivroDto() {
		return livroDto;
	}
	public void setLivroDto(LivroDto livroDto) {
		this.livroDto = livroDto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	
}
