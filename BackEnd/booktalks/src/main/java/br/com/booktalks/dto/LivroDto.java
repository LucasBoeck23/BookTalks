package br.com.booktalks.dto;

import java.time.LocalDate;

import br.com.booktalks.enums.Categoria;

public class LivroDto {
	Integer livro_id;
	String nome;
	String ISBN;
	String descricao;
	String editora;
	Integer paginas;
	Double preco;
	Categoria categoria;
	Double avaliacao;
	PessoaDto autor;
	LocalDate dataPublicacao;
	
	public Integer getLivro_id() {
		return livro_id;
	}
	public void setLivro_id(Integer livro_id) {
		this.livro_id = livro_id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getPaginas() {
		return paginas;
	}
	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Double getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(Double avaliacao) {
		this.avaliacao = avaliacao;
	}
	public PessoaDto getAutor() {
		return autor;
	}
	public void setAutor(PessoaDto autor) {
		this.autor = autor;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}
	public void setDataPublicacao(LocalDate dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}
	
	
	
}
