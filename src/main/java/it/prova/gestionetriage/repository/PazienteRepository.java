package it.prova.gestionetriage.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import it.prova.gestionetriage.model.Paziente;

public interface PazienteRepository extends CrudRepository<Paziente, Long>, PagingAndSortingRepository<Paziente, Long>, JpaSpecificationExecutor<Paziente> {

}
