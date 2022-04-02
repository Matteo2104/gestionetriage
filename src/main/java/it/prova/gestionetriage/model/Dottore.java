package it.prova.gestionetriage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "dottore")
public class Dottore {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "cognome")
	private String cognome;
	
	@Column(name = "codiceDipendente")
	private String codiceDipendente;
	
	@OneToOne(mappedBy = "dottore")
	private Paziente pazienteAttualmenteInVisita;
	
	
	public Dottore() {}
	public Dottore(Long id, String nome, String cognome, String codiceDipendente) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDipendente = codiceDipendente;
	}
	

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getCodiceDipendente() {
		return codiceDipendente;
	}

	public Paziente getPazienteAttualmenteInVisita() {
		return pazienteAttualmenteInVisita;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setCodiceDipendente(String codiceDipendente) {
		this.codiceDipendente = codiceDipendente;
	}

	public void setPazienteAttualmenteInVisita(Paziente pazienteAttualmenteInVisita) {
		this.pazienteAttualmenteInVisita = pazienteAttualmenteInVisita;
	}

	
	@Override
	public String toString() {
		return "Dottore [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", codiceDipendente="
				+ codiceDipendente + "]";
	}
	
	
}
