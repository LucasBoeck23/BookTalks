package br.com.booktalks.dto;

public class RepublicadoDto {

	Integer republicadoId;
	PessoaDto pessoa;
	PublicacaoDto publicacao;
	
	public Integer getRepublicadoId() {
		return republicadoId;
	}
	public void setRepublicadoId(Integer republicadoId) {
		this.republicadoId = republicadoId;
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
