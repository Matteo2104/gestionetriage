package it.prova.gestionetriage.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import it.prova.gestionetriage.model.Dottore;

public interface DottoreRepository extends CrudRepository<Dottore, Long>, PagingAndSortingRepository<Dottore, Long>, JpaSpecificationExecutor<Dottore> {
	@Query("from Dottore d left join fetch d.pazienteAttualmenteInVisita where d.id = ?1")
	public Optional<Dottore> findByIdEager(Long id);
}
