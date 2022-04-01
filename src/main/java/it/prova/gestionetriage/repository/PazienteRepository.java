package it.prova.gestionetriage.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import it.prova.gestionetriage.model.Paziente;

public interface PazienteRepository extends CrudRepository<Paziente, Long>, PagingAndSortingRepository<Paziente, Long>, JpaSpecificationExecutor<Paziente> {
	@Query("from Paziente p left join fetch p.dottore where p.id = ?1")
	public Optional<Paziente> findByIdEager(Long id);
}
