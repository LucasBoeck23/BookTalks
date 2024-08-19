package br.com.booktalks.dto;

import java.time.LocalDate;

public class PublicacaoDto {
	Integer publicacao_id;
	PessoaDto pessoa;
	String conteudo;
	int numeroLikes;
	int numeroRepublicados;
	int numeroFavoritos;
	int numeroComentarios;
	LocalDate dataPublicacao;
	
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
	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}
	public void setDataPublicacao(LocalDate dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}
	public int getNumeroComentarios() {
		return numeroComentarios;
	}
	public void setNumeroComentarios(int numeroComentarios) {
		this.numeroComentarios = numeroComentarios;
	}
	
}