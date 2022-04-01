package it.prova.gestionetriage.service.paziente;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionetriage.model.Paziente;

public interface PazienteService {

	public List<Paziente> listAllElements();

	public Paziente inserisciNuovo(Paziente paziente);

	public Paziente caricaSingoloElemento(Long id);

	public Paziente aggiorna(Paziente paziente);

	public void rimuovi(Paziente paziente);

	public Paziente caricaSingoloElementoEager(Long id);

	public Page<Paziente> findByExample(Paziente example, Integer pageNo, Integer pageSize, String sortBy);

}
