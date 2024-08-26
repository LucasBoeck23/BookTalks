package br.com.booktalks.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "avaliacaco")
public class Avaliacao {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer avaliacao_id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
    
    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;
    
    @Column
    private String titulo;
    
    @Column
    @NotNull
    private String comentario;
    
    @Column
    @NotNull
    private Double nota;
    
    @Column
    private LocalDate data;

	public Integer getAvaliacao_id() {
		return avaliacao_id;
	}

	public void setAvaliacao_id(Integer avaliacao_id) {
		this.avaliacao_id = avaliacao_id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
    

}
