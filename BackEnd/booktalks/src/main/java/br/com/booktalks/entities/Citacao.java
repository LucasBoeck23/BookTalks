//package br.com.booktalks.entities;
//import java.time.LocalDate;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//@Entity
//@Table(name = "citacao")
//public class Citacao {
//
// 	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer citacaoId;
//	 	
// 	@ManyToOne
//	@JoinColumn(name = "pessoa_id")
//	private Pessoa pessoa;
//	
//	@ManyToOne
//	@JoinColumn(name = "publicacao_id")
//	private Publicacao publicacao;
//	
//	@Column
//	private String descricao;
//	
//	@Column
//	private int numeroLikes;
//	
//	@Column
//	private int numeroRepublicados;
//	
//	@Column
//	private int numeroFavoritos;
//	
//	@Column
//	private LocalDate dataCitacao;
//
//	public Integer getCitacaoId() {
//		return citacaoId;
//	}
//
//	public void setCitacaoId(Integer citacaoId) {
//		this.citacaoId = citacaoId;
//	}
//
//	public Pessoa getPessoa() {
//		return pessoa;
//	}
//
//	public void setPessoa(Pessoa pessoa) {
//		this.pessoa = pessoa;
//	}
//
//	public Publicacao getPublicacao() {
//		return publicacao;
//	}
//
//	public void setPublicacao(Publicacao publicacao) {
//		this.publicacao = publicacao;
//	}
//
//	public String getDescricao() {
//		return descricao;
//	}
//
//	public void setDescricao(String descricao) {
//		this.descricao = descricao;
//	}
//
//	public int getNumeroLikes() {
//		return numeroLikes;
//	}
//
//	public void setNumeroLikes(int numeroLikes) {
//		this.numeroLikes = numeroLikes;
//	}
//
//	public int getNumeroRepublicados() {
//		return numeroRepublicados;
//	}
//
//	public void setNumeroRepublicados(int numeroRepublicados) {
//		this.numeroRepublicados = numeroRepublicados;
//	}
//
//	public int getNumeroFavoritos() {
//		return numeroFavoritos;
//	}
//
//	public void setNumeroFavoritos(int numeroFavoritos) {
//		this.numeroFavoritos = numeroFavoritos;
//	}
//
//	public LocalDate getDataCitacao() {
//		return dataCitacao;
//	}
//
//	public void setDataCitacao(LocalDate dataCitacao) {
//		this.dataCitacao = dataCitacao;
//	}
//	
//}
