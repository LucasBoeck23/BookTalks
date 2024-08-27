package br.com.booktalks.entities;

import java.time.LocalDate;
import java.util.List;

import br.com.booktalks.enums.Cargo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pessoa_id;
	
	@Column(unique = true)
	@NotBlank
	private String nomeUsuario;
	
	@Column
	@NotNull
	private String nome;
	
	@Column
	@NotNull
	private String sobrenome;
	
	@Column
	private LocalDate dataNascimento;
	
	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Email inválido")
	private String email;
	
	@Column
	@NotBlank
	private String senha;
	
	@Column
	private int numeroSeguidores;
	
	@Column
	private int numeroSeguindo;
	
	
	@Column
	@Enumerated(EnumType.STRING)
	private Cargo cargo;
	
	@Column
	private LocalDate dataCriacao;
	
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private List<Endereco> endereco;
	
	@OneToMany(mappedBy = "autor")
	private List<Livro> livros;
	
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private List<Publicacao> publicacoes;

	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private List<Like> Curtidas;
	
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private List<Republicado> Republicados;
	
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private List<Favorito> favoritados;
	
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private List<Comentario> comentarios;
	
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private List<Seguidor> seguidores;
	
	@OneToMany(mappedBy = "seguindo", cascade = CascadeType.ALL)
	private List<Seguidor> seguindo;
	
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private List<Avaliacao> avaliacao;
	
	@OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private Carrinho carrinho;
	
	
	
	public Integer getPessoa_id() {
		return pessoa_id;
	}

	public void setPessoa_id(Integer pessoa_id) {
		this.pessoa_id = pessoa_id;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public List<Seguidor> getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(List<Seguidor> seguidores) {
		this.seguidores = seguidores;
	}

	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public List<Publicacao> getPublicacoes() {
		return publicacoes;
	}

	public void setPublicacoes(List<Publicacao> publicacoes) {
		this.publicacoes = publicacoes;
	}

	public List<Like> getCurtidas() {
		return Curtidas;
	}

	public void setCurtidas(List<Like> curtidas) {
		Curtidas = curtidas;
	}

	public List<Republicado> getRepublicados() {
		return Republicados;
	}

	public void setRepublicados(List<Republicado> republicados) {
		Republicados = republicados;
	}
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public List<Favorito> getFavoritados() {
		return favoritados;
	}

	public void setFavoritados(List<Favorito> favoritados) {
		this.favoritados = favoritados;
	}

	public List<Seguidor> getSeguindo() {
		return seguindo;
	}

	public void setSeguindo(List<Seguidor> seguindo) {
		this.seguindo = seguindo;
	}

	public int getNumeroSeguidores() {
		return numeroSeguidores;
	}

	public void setNumeroSeguidores(int numeroSeguidores) {
		this.numeroSeguidores = numeroSeguidores;
	}

	public int getNumeroSeguindo() {
		return numeroSeguindo;
	}

	public void setNumeroSeguindo(int numeroSeguindo) {
		this.numeroSeguindo = numeroSeguindo;
	}

	public List<Avaliacao> getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(List<Avaliacao> avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}
	
	
}
