package it.prova.gestionetriage.service.dottore;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionetriage.model.Dottore;

public interface DottoreService {

	public List<Dottore> listAllElements();

	public Dottore caricaSingoloElemento(Long id);

	public Dottore inserisciNuovo(Dottore dottore);

	public Dottore aggiorna(Dottore dottore);

	public Dottore caricaSingoloElementoEager(Long id);

	public void rimuovi(Dottore dottore);

	public Page<Dottore> findByExample(Dottore example, Integer pageNo, Integer pageSize, String sortBy);

}
