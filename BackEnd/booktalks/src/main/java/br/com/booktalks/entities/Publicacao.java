package br.com.booktalks.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	@Column
	@NotNull
	private String conteudo;
	
	@Column
	private Integer likes;
	
	@Column
	private Integer republicado;
	
	@Column
	private Integer favorito;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
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

	public Integer getPublicacao_id() {
		return publicacao_id;
	}

	public void setPublicacao_id(Integer publicacao_id) {
		this.publicacao_id = publicacao_id;
	}
	
}
