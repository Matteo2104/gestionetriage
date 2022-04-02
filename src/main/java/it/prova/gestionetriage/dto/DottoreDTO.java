package it.prova.gestionetriage.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import it.prova.gestionetriage.model.Dottore;
import it.prova.gestionetriage.model.Paziente;

public class DottoreDTO {
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
	
	public DottoreDTO() {}
	public DottoreDTO(Long id, String nome, String cognome, String codiceDipendente) {
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
	
	
	public Dottore buildDottoreModel() {
		return new Dottore(this.id, this.nome, this.cognome, this.codiceDipendente);
	}

	public static DottoreDTO buildDottoreDTOFromModel(Dottore dottoreModel, boolean includePaziente) {
		DottoreDTO result = new DottoreDTO(dottoreModel.getId(), dottoreModel.getNome(), dottoreModel.getCognome(),
				dottoreModel.getCodiceDipendente());
		
		/*
		if (includeTratte)
			result.setDottore(DottoreDTO.createTrattaDTOSetFromModelSet(pazienteModel.getDottore(), false));
		*/
		return result;
	}

	public static List<DottoreDTO> buildPazienteDTOListFromModelList(List<Dottore> modelListInput, boolean includePaziente) {
		return modelListInput.stream().map(dottoreEntity -> {
			DottoreDTO result = DottoreDTO.buildDottoreDTOFromModel(dottoreEntity, includePaziente);
			
//			if(includeTratte)
//				result.setTratte(TrattaDTO.createTrattaDTOSetFromModelSet(airbusEntity.getTratte(), false));
			
			return result;
		}).collect(Collectors.toList());
	}
	
	
	@Override
	public String toString() {
		return "DottoreDTO [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", codiceDipendente="
				+ codiceDipendente + "]";
	}
	
	
}
