package it.prova.gestionetriage.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "paziente")
public class Paziente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "cognome")
	private String cognome;
	
	@Column(name = "codiceFiscale")
	private String codiceFiscale;
	
	@Column(name = "dataRegistrazione")
	private Date dataRegistrazione;
	
	@Column(name = "stato")
	@Enumerated(EnumType.STRING)
	private StatoPaziente stato;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "paziente_dottore", joinColumns = @JoinColumn(name = "paziente_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "dottore_id", referencedColumnName = "ID"))
	private Dottore dottore;

	
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}

	public StatoPaziente getStato() {
		return stato;
	}

	public Dottore getDottore() {
		return dottore;
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

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public void setStato(StatoPaziente stato) {
		this.stato = stato;
	}

	public void setDottore(Dottore dottore) {
		this.dottore = dottore;
	}
	
	

	@Override
	public String toString() {
		return "Paziente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", codiceFiscale=" + codiceFiscale
				+ ", dataRegistrazione=" + dataRegistrazione + ", stato=" + stato + "]";
	}
	
	
	
}
