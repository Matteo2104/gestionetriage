package it.prova.gestionetriage.service.utente;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.prova.gestionetriage.model.Paziente;
import it.prova.gestionetriage.model.StatoUtente;
import it.prova.gestionetriage.model.Utente;
import it.prova.gestionetriage.repository.PazienteRepository;
import it.prova.gestionetriage.repository.UtenteRepository;

@Service
public class UtenteServiceImpl implements UtenteService {
	@Autowired
	private UtenteRepository repository;
	
	@Override
	@Transactional
	public List<Utente> listAllElements() {
		return (List<Utente>)repository.findAll();
	}
	
	@Override
	@Transactional
	public List<Utente> listAllElementsEager() {
		return (List<Utente>)repository.findAllEager();
	}
	
	@Override
	@Transactional
	public Utente caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public Utente caricaSingoloElementoEager(Long id) {
		return repository.findByIdEager(id).orElse(null);
	}
	
	@Override
	@Transactional
	public Utente inserisciNuovo(Utente utente) {
		return repository.save(utente);
	}
	
	@Override
	@Transactional
	public Utente aggiorna(Utente utente) {
		return repository.save(utente);
	}
	
	@Override
	@Transactional
	public Utente disabilita(Utente utente) {
		utente.setStato(StatoUtente.DISABILITATO);
		return repository.save(utente);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Utente> findByExample(Utente example, Integer pageNo, Integer pageSize, String sortBy) {
		Specification<Utente> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();
			
		

			if (StringUtils.isNotEmpty(example.getNome()))
				predicates.add(cb.like(cb.upper(root.get("nome")), "%" + example.getNome().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getCognome()))
				predicates.add(cb.like(cb.upper(root.get("cognome")), "%" + example.getCognome().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getUsername()))
				predicates.add(cb.like(cb.upper(root.get("username")), "%" + example.getUsername().toUpperCase() + "%"));

			if (example.getDataRegistrazione() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataRegistrazione"), example.getDataRegistrazione()));
			
			if (example.getStato() != null)
				predicates.add(cb.equal(root.get("stato"), example.getStato()));
		
			
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));

		};
		
		Pageable paging = null;
		// se non passo parametri di paginazione non ne tengo conto
		if (pageSize == null || pageSize < 10)
			paging = Pageable.unpaged();
		else
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return repository.findAll(specificationCriteria, paging);
	}
	
	/*
	
	@Override
	@Transactional
	public void rimuovi(Paziente paziente) {
		repository.delete(paziente);
	}
	
	*/
}
