package br.com.booktalks.entities;

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
	private List<Republicado> pessoasRepublicados;
	
	@OneToMany(mappedBy = "publicacao", cascade = CascadeType.ALL)
	private List<Citacao> pessoasCitacoes;
	
	@Column
	private int numeroLikes;
	
	@Column
	@NotNull
	private String conteudo;
	
	@Column
	private int numeroRepublicados;
	
	@Column
	private int numeroFavoritos;

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

	public List<Citacao> getPessoasCitacoes() {
		return pessoasCitacoes;
	}

	public void setPessoasCitacoes(List<Citacao> pessoasCitacoes) {
		this.pessoasCitacoes = pessoasCitacoes;
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
}
