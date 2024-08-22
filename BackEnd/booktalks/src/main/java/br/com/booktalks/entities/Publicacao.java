package br.com.booktalks.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "publicacao")
public class Publicacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer publicacao_id;
	
	@ManyToOne
	@JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
	
	@OneToMany(mappedBy = "publicacao", cascade = CascadeType.ALL)
	private List<Like> pessoasCurtidas;
	
	@OneToMany(mappedBy = "publicacao", cascade = CascadeType.ALL)
	private List<Favorito> pessoasFavoritos;
	
	@OneToMany(mappedBy = "publicacao", cascade = CascadeType.ALL)
	private List<Republicado> pessoasRepublicados;
	
//	@OneToMany(mappedBy = "publicacao", cascade = CascadeType.ALL)
//	private List<Citacao> pessoasCitacoes;

	@OneToMany(mappedBy = "publicacao", cascade = CascadeType.ALL)
	private List<Comentario> pessoasComentarios;
	
	@Column
	private int numeroLikes;
	
	@Column
	private LocalDate dataPublicacao;
	
	@Column
	@NotNull
	private String conteudo;
	
	@Column
	private int numeroRepublicados;
	
	@Column
	private int numeroFavoritos;

	@Column
	private int numeroComentarios;

	public Integer getPublicacao_id() {
		return publicacao_id;
	}

	public void setPublicacao_id(Integer publicacao_id) {
		this.publicacao_id = publicacao_id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Like> getPessoasCurtidas() {
		return pessoasCurtidas;
	}

	public void setPessoasCurtidas(List<Like> pessoasCurtidas) {
		this.pessoasCurtidas = pessoasCurtidas;
	}

	public List<Republicado> getPessoasRepublicados() {
		return pessoasRepublicados;
	}

	public void setPessoasRepublicados(List<Republicado> pessoasRepublicados) {
		this.pessoasRepublicados = pessoasRepublicados;
	}

	public int getNumeroLikes() {
		return numeroLikes;
	}

	public void setNumeroLikes(int numeroLikes) {
		this.numeroLikes = numeroLikes;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
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

	public List<Comentario> getPessoasComentarios() {
		return pessoasComentarios;
	}

	public void setPessoasComentarios(List<Comentario> pessoasComentarios) {
		this.pessoasComentarios = pessoasComentarios;
	}

	public int getNumeroComentarios() {
		return numeroComentarios;
	}

	public void setNumeroComentarios(int numeroComentarios) {
		this.numeroComentarios = numeroComentarios;
	}

	public List<Favorito> getPessoasFavoritos() {
		return pessoasFavoritos;
	}

	public void setPessoasFavoritos(List<Favorito> pessoasFavoritos) {
		this.pessoasFavoritos = pessoasFavoritos;
	}
	
}
