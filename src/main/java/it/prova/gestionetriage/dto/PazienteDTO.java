package it.prova.gestionetriage.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import it.prova.gestionetriage.model.Paziente;
import it.prova.gestionetriage.model.StatoPaziente;

public class PazienteDTO {
	
	
	private Long id;
	
	private String nome;
	
	private String cognome;
	
	private String codiceFiscale;
	
	private Date dataRegistrazione;
	
	private StatoPaziente stato;
	
	private DottoreDTO dottore;
	
	public PazienteDTO() {}
	public PazienteDTO(Long id, String nome, String cognome, String codiceFiscale, Date dataRegistrazione, StatoPaziente stato) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataRegistrazione = dataRegistrazione;
		this.stato = stato;
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
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}
	public StatoPaziente getStato() {
		return stato;
	}
	public DottoreDTO getDottore() {
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
	public void setDottore(DottoreDTO dottore) {
		this.dottore = dottore;
	}
	
	
	public Paziente buildPazienteModel() {
		return new Paziente(this.id, this.nome, this.cognome, this.codiceFiscale, this.dataRegistrazione, this.stato);
	}

	public static PazienteDTO buildPazienteDTOFromModel(Paziente pazienteModel, boolean includeDottore) {
		PazienteDTO result = new PazienteDTO(pazienteModel.getId(), pazienteModel.getNome(), pazienteModel.getCognome(),
				pazienteModel.getCodiceFiscale(), pazienteModel.getDataRegistrazione(), pazienteModel.getStato());
		
		/*
		if (includeTratte)
			result.setDottore(DottoreDTO.createTrattaDTOSetFromModelSet(pazienteModel.getDottore(), false));
		*/
		return result;
	}

	public static List<PazienteDTO> buildPazienteDTOListFromModelList(List<Paziente> modelListInput, boolean includeDottore) {
		return modelListInput.stream().map(pazienteEntity -> {
			PazienteDTO result = PazienteDTO.buildPazienteDTOFromModel(pazienteEntity, includeDottore);
			
//			if(includeTratte)
//				result.setTratte(TrattaDTO.createTrattaDTOSetFromModelSet(airbusEntity.getTratte(), false));
			
			return result;
		}).collect(Collectors.toList());
	}
	
	
	
	
	@Override
	public String toString() {
		return "PazienteDTO [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", codiceFiscale=" + codiceFiscale
				+ ", dataRegistrazione=" + dataRegistrazione + ", stato=" + stato + "]";
	}
	
	
	
}
