package it.prova.gestionetriage.service.paziente;

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
import it.prova.gestionetriage.repository.PazienteRepository;

@Service
public class PazienteServiceImpl implements PazienteService {
	@Autowired
	private PazienteRepository repository;
	
	@Override
	@Transactional
	public List<Paziente> listAllElements() {
		return (List<Paziente>)repository.findAll();
	}
	
	@Override
	@Transactional
	public Paziente inserisciNuovo(Paziente paziente) {
		return repository.save(paziente);
	}
	
	@Override
	@Transactional
	public Paziente aggiorna(Paziente paziente) {
		return repository.save(paziente);
	}
	
	@Override
	@Transactional
	public Paziente caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public Paziente caricaSingoloElementoEager(Long id) {
		return repository.findByIdEager(id).orElse(null);
	}
	
	@Override
	@Transactional
	public void rimuovi(Paziente paziente) {
		repository.delete(paziente);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Paziente> findByExample(Paziente example, Integer pageNo, Integer pageSize, String sortBy) {
		Specification<Paziente> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();
			
		

			if (StringUtils.isNotEmpty(example.getNome()))
				predicates.add(cb.like(cb.upper(root.get("nome")), "%" + example.getNome().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getCognome()))
				predicates.add(cb.like(cb.upper(root.get("cognome")), "%" + example.getCognome().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getCodiceFiscale()))
				predicates.add(cb.like(cb.upper(root.get("codiceFiscale")), "%" + example.getCodiceFiscale().toUpperCase() + "%"));

			if (example.getDataRegistrazione() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataRegistrazione"), example.getDataRegistrazione()));
			
			if (example.getStato() != null)
				predicates.add(cb.equal(root.get("stato"), example.getStato()));
			
			
			// inserire anche dottore ???
		
			
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
}
