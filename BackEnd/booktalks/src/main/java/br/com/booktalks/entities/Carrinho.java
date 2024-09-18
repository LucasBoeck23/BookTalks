package br.com.booktalks.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "carrinho")
public class Carrinho {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer carrinho_id;
	
	 @OneToOne
	 @JoinColumn(name = "pessoa_id")
	 private Pessoa pessoa;
	 
	 @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL, orphanRemoval = true)
	 private List<ItemCarrinho> itens;
	 
	 @Column
	 private int quantidadeItens;

	public Integer getCarrinho_id() {
		return carrinho_id;
	}

	public void setCarrinho_id(Integer carrinho_id) {
		this.carrinho_id = carrinho_id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<ItemCarrinho> getItens() {
		return itens;
	}

	public void setItens(List<ItemCarrinho> itens) {
		this.itens = itens;
	}

	public int getQuantidadeItens() {
		return quantidadeItens;
	}

	public void setQuantidadeItens(int quantidadeItens) {
		this.quantidadeItens = quantidadeItens;
	}
	 
	
}
