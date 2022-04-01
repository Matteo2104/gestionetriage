package it.prova.gestionetriage.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionetriage.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long>, PagingAndSortingRepository<Utente, Long>, JpaSpecificationExecutor<Utente> {

}
