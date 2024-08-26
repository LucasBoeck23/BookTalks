package br.com.booktalks.dto;

import java.time.LocalDate;

public class AvaliacaoDto {
	 Integer avaliacao_id;
	 PessoaDto pessoa;
	 LivroDto livro;
	 String titulo;
	 String comentario;
	 Double nota;
	 LocalDate data;
	 
	public Integer getAvaliacao_id() {
		return avaliacao_id;
	}
	public void setAvaliacao_id(Integer avaliacao_id) {
		this.avaliacao_id = avaliacao_id;
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
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Double getNota() {
		return nota;
	}
	public void setNota(Double nota) {
		this.nota = nota;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	 
	 
}
