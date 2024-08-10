package br.com.booktalks.dto;

public class CitacaoDto {
	
	Integer citacaoId;
	String descricao;
	PessoaDto pessoa;
	PublicacaoDto publicacao;
	
	public Integer getCitacaoId() {
		return citacaoId;
	}
	public void setCitacaoId(Integer citacaoId) {
		this.citacaoId = citacaoId;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public PessoaDto getPessoa() {
		return pessoa;
	}
	public void setPessoa(PessoaDto pessoa) {
		this.pessoa = pessoa;
	}
	public PublicacaoDto getPublicacao() {
		return publicacao;
	}
	public void setPublicacao(PublicacaoDto publicacao) {
		this.publicacao = publicacao;
	}
	
}
