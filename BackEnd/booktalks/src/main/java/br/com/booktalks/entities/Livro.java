package br.com.booktalks.entities;

import br.com.booktalks.enums.Categoria;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "livro")
public class Livro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer livro_id;
	
	@Column
	private String nome;
	
	@Column(unique = true)
    @Pattern(regexp = "^(97[89])\\d{10}$", message = "ISBN inválido")
	private String ISBN;
	
	@Column
	private String descricao;
	
	@Column
	private Integer paginas;
	
	@Column
	private Double preco;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	
	@Column
	@Min(0)
    @Max(5)
	private Double avaliacao;
	
	@NotNull
	@ManyToOne
	private Pessoa autor;

	public Integer getLivro_id() {
		return livro_id;
	}

	public void setLivro_id(Integer livro_id) {
		this.livro_id = livro_id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getPaginas() {
		return paginas;
	}

	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Double getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Double avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Pessoa getAutor() {
		return autor;
	}

	public void setAutor(Pessoa autor) {
		this.autor = autor;
	}
	//capadolivro
	
}
