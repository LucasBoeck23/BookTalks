package br.com.booktalks.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "republicado")
public class Republicado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer republicadoId;
	 
	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	
	@ManyToOne
	@JoinColumn(name = "publicacao_id")
	private Publicacao publicacao;

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

	public Integer getRepublicadoId() {
		return republicadoId;
	}

	public void setRepublicadoId(Integer republicadoId) {
		this.republicadoId = republicadoId;
	}
}
