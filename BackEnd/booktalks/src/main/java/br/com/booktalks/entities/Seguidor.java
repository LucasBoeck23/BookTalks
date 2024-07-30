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
	    private Integer id;
	    
	    @ManyToOne
	    @JoinColumn(name = "seguidores_id")
	    private Pessoa seguidores;
	    
	    @ManyToOne
	    @JoinColumn(name = "seguindo_id")
	    private Pessoa seguindo;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Pessoa getSeguidores() {
			return seguidores;
		}

		public void setSeguidores(Pessoa seguidores) {
			this.seguidores = seguidores;
		}

		public Pessoa getSeguindo() {
			return seguindo;
		}

		public void setSeguindo(Pessoa seguindo) {
			this.seguindo = seguindo;
		}

		
	}

