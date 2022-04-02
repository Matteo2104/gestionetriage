package it.prova.gestionetriage.service.utente;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionetriage.model.Paziente;
import it.prova.gestionetriage.model.Utente;

public interface UtenteService {

	public List<Utente> listAllElements();

	public List<Utente> listAllElementsEager();

	public Utente caricaSingoloElementoEager(Long id);

	public Utente inserisciNuovo(Utente utente);

	public Utente caricaSingoloElemento(Long id);

	public Utente aggiorna(Utente utente);

	public Page<Utente> findByExample(Utente example, Integer pageNo, Integer pageSize, String sortBy);

}
