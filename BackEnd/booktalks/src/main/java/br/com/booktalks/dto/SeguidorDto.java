package br.com.booktalks.dto;

public class SeguidorDto {
	Integer seguidor_Id;
	PessoaDto pessoa;
	PessoaDto seguindo;
	
	public Integer getSeguidor_Id() {
		return seguidor_Id;
	}
	public void setSeguidor_Id(Integer seguidor_Id) {
		this.seguidor_Id = seguidor_Id;
	}
	public PessoaDto getPessoa() {
		return pessoa;
	}
	public void setPessoa(PessoaDto pessoa) {
		this.pessoa = pessoa;
	}
	public PessoaDto getSeguindo() {
		return seguindo;
	}
	public void setSeguindo(PessoaDto seguindo) {
		this.seguindo = seguindo;
	}
	
	
}
