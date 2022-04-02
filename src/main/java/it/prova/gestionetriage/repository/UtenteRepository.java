package it.prova.gestionetriage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionetriage.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long>, PagingAndSortingRepository<Utente, Long>, JpaSpecificationExecutor<Utente> {
	@Query("select distinct u from Utente u left join fetch u.ruoli")
	public List<Utente> findAllEager();
	
	@Query("from Utente u left join fetch u.ruoli where u.id = ?1")
	public Optional<Utente> findByIdEager(Long id);
}
