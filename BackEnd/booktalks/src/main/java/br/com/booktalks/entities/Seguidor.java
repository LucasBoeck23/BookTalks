package br.com.booktalks.entities;

	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.JoinColumn;
	import jakarta.persistence.ManyToOne;
	import jakarta.persistence.Table;

	@Entity
	@Table(name = "seguidor")
	public class Seguidor {
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer seguidor_Id;
	    
	    @ManyToOne
		@JoinColumn(name = "pessoa_seguindo_id")
		private Pessoa seguindo;
	    
	    @ManyToOne
	    @JoinColumn(name = "pessoa_id")
	    private Pessoa pessoa;

		public Integer getSeguidor_Id() {
			return seguidor_Id;
		}

		public void setSeguidor_Id(Integer seguidor_Id) {
			this.seguidor_Id = seguidor_Id;
		}

		public Pessoa getSeguindo() {
			return seguindo;
		}

		public void setSeguindo(Pessoa seguindo) {
			this.seguindo = seguindo;
		}

		public Pessoa getPessoa() {
			return pessoa;
		}

		public void setPessoa(Pessoa pessoa) {
			this.pessoa = pessoa;
		}

		
		
	}

