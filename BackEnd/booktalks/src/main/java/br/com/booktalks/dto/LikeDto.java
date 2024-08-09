package br.com.booktalks.dto;

public class LikeDto {

	PessoaDto pessoa;
	PublicacaoDto publicacao;
	
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
