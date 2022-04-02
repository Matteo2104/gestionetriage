package it.prova.gestionetriage.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import it.prova.gestionetriage.model.StatoUtente;
import it.prova.gestionetriage.model.Utente;

public class UtenteDTO {
	
	private Long id;
	
	private String nome;
	
	private String cognome;
	
	private String username;
	
	private String password;
	
	private Date dataRegistrazione;
	
	private StatoUtente stato;
	
	private Long[] ruoliIds;
	
	
	public UtenteDTO() {}
	public UtenteDTO(Long id, String nome, String cognome, String username, String password, Date dataRegistrazione, StatoUtente stato) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
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
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}
	public StatoUtente getStato() {
		return stato;
	}
	public Long[] getRuoliIds() {
		return ruoliIds;
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
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}
	public void setStato(StatoUtente stato) {
		this.stato = stato;
	}
	public void setRuoliIds(Long[] ruoliIds) {
		this.ruoliIds = ruoliIds;
	}
	
	
	
	public Utente buildUtenteModel() {
		return new Utente(this.id, this.nome, this.cognome, this.username, this.password, this.dataRegistrazione, this.stato);
	}

	public static UtenteDTO buildUtenteDTOFromModel(Utente utenteModel, boolean includeRuoli) {
		UtenteDTO result = new UtenteDTO(utenteModel.getId(), utenteModel.getNome(), utenteModel.getCognome(),
				utenteModel.getUsername(), utenteModel.getPassword(), utenteModel.getDataRegistrazione(), utenteModel.getStato());
		

		if (includeRuoli)
			result.ruoliIds = utenteModel.getRuoli().stream().map(r -> r.getId()).collect(Collectors.toList())
					.toArray(new Long[] {});
		

		return result;
	}
	
	
	public static List<UtenteDTO> createUtenteDTOListFromModelList(List<Utente> modelListInput, boolean includeRuoli) {
		return modelListInput.stream().map(utenteEntity -> {
			return UtenteDTO.buildUtenteDTOFromModel(utenteEntity, includeRuoli);
		}).collect(Collectors.toList());
	}
	
}
