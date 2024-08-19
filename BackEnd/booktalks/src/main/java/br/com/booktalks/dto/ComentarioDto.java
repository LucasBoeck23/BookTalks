package br.com.booktalks.dto;

public class ComentarioDto {

	private Integer comentario_id;
	private String comentario;
	private PessoaDto pessoa;
	private PublicacaoDto publicacao;
	
	public Integer getComentario_id() {
		return comentario_id;
	}
	public void setComentario_id(Integer comentario_id) {
		this.comentario_id = comentario_id;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
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
