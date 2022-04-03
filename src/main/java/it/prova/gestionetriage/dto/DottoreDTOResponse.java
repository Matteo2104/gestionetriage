package it.prova.gestionetriage.dto;

public class DottoreDTOResponse {
private Long id;
	
	private String nome;
	
	private String cognome;
	
	private String codiceDipendente;
	
	private boolean inServizio;
	private boolean inVisita;
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
	public boolean isInServizio() {
		return inServizio;
	}
	public boolean isInVisita() {
		return inVisita;
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
	public void setInServizio(boolean inServizio) {
		this.inServizio = inServizio;
	}
	public void setInVisita(boolean inVisita) {
		this.inVisita = inVisita;
	}
	
	
	
	@Override
	public String toString() {
		return "DottoreDTOResponse [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", codiceDipendente="
				+ codiceDipendente + ", inServizio=" + inServizio + ", inVisita=" + inVisita + "]";
	}
	
	
	
	
}
