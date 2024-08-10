package br.com.booktalks.dto;

public class PublicacaoDto {
	Integer publicacao_id;
	PessoaDto pessoa;
	String conteudo;
	int numeroLikes;
	int numeroRepublicados;
	int numeroFavoritos;
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
	public int getNumeroLikes() {
		return numeroLikes;
	}
	public void setNumeroLikes(int numeroLikes) {
		this.numeroLikes = numeroLikes;
	}
	public int getNumeroRepublicados() {
		return numeroRepublicados;
	}
	public void setNumeroRepublicados(int numeroRepublicados) {
		this.numeroRepublicados = numeroRepublicados;
	}
	public int getNumeroFavoritos() {
		return numeroFavoritos;
	}
	public void setNumeroFavoritos(int numeroFavoritos) {
		this.numeroFavoritos = numeroFavoritos;
	}
	
}