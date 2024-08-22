package br.com.booktalks.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "favorito")
@Entity
public class Favorito {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer favorito_id;
	 
	 @ManyToOne
	 @JoinColumn(name = "pessoa_id")
	 private Pessoa pessoa;
	 
	 @ManyToOne
	 @JoinColumn(name = "publicacao_id")
	 private Publicacao publicacao;

	public Integer getFavorito_id() {
		return favorito_id;
	}

	public void setFavorito_id(Integer favorito_id) {
		this.favorito_id = favorito_id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Publicacao getPublicacao() {
		return publicacao;
	}

	public void setPublicacao(Publicacao publicacao) {
		this.publicacao = publicacao;
	}
	 
	 
}
