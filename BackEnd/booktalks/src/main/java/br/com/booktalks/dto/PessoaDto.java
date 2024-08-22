package br.com.booktalks.dto;

import java.time.LocalDate;

import br.com.booktalks.enums.Cargo;

public class PessoaDto {
	Integer pessoa_id;
	String nomeUsuario;
	String nome;
	String sobrenome;
	LocalDate dataNascimento;
	String email;
	String senha;
	LocalDate dataCriacao;
	int numeroSeguidores;
	int numeroSeguindo;
	Cargo cargo;
	
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
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
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
	
	
}
