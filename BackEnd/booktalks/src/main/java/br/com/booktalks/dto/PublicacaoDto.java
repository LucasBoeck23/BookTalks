package br.com.booktalks.dto;

public class PublicacaoDto {
	Integer publicacao_id;
	PessoaDto pessoa;
	String conteudo;
	Integer likes;
	Integer republicado;
	Integer favorito;
	
	
	public Integer getPublicacao_id() {
		return publicacao_id;
	}
	public void setPublicacao_id(Integer publicacao_id) {
		this.publicacao_id = publicacao_id;
	}
	public PessoaDto getPessoa() {
		return pessoa;
	}
	public void setPessoa(PessoaDto pessoa) {
		this.pessoa = pessoa;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public Integer getLikes() {
		return likes;
	}
	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	public Integer getRepublicado() {
		return republicado;
	}
	public void setRepublicado(Integer republicado) {
		this.republicado = republicado;
	}
	public Integer getFavorito() {
		return favorito;
	}
	public void setFavorito(Integer favorito) {
		this.favorito = favorito;
	}
}