package br.com.booktalks.dto;

public class FavoritoDto {
	Integer favorito_id;
	PessoaDto pessoa;
	PublicacaoDto publicacao;
	public PessoaDto getPessoa() {
		return pessoa;
	}
	public void setPessoa(PessoaDto pessoa) {
		this.pessoa = pessoa;
	}
	public PublicacaoDto getPublicacao() {
		return publicacao;
	}
	public void setPublicacao(PublicacaoDto publicacao) {
		this.publicacao = publicacao;
	}
	public Integer getFavorito_id() {
		return favorito_id;
	}
	public void setFavorito_id(Integer favorito_id) {
		this.favorito_id = favorito_id;
	}
}
