package br.com.booktalks.entities;

import java.time.LocalDate;
import java.util.List;

import br.com.booktalks.enums.Cargo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
	@Enumerated(EnumType.STRING)
	private Cargo cargo;
	
	@Column
	@OneToMany(mappedBy = "seguidores")
	private List<Seguidor> seguindo;

	@Column
	@OneToMany(mappedBy = "seguindo")
	private List<Seguidor> seguidores;
	
	@Column
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private List<Endereco> endereco;
	
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

	public List<Seguidor> getSeguindo() {
		return seguindo;
	}

	public void setSeguindo(List<Seguidor> seguindo) {
		this.seguindo = seguindo;
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



//	
//	@Column
//	private List<Livro>carrinho;
//
//	@Column
//	private List<Livro> favoritos;
//	
//	@Column
//	private List<Publicacao> publicacoes;
//
//	@Column
//	private List<Publicacao> cutidos;
//	
//	@Column
//	private List<Publicacao> salvos;
//	
	
	
}
