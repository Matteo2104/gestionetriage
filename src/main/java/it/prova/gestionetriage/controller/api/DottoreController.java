package it.prova.gestionetriage.controller.api;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import it.prova.gestionetriage.controller.api.exception.DottoreNotEmptyException;
import it.prova.gestionetriage.controller.api.exception.DottoreNotFoundException;
import it.prova.gestionetriage.controller.api.exception.IdNotNullForInsertException;
import it.prova.gestionetriage.dto.DottoreDTO;
import it.prova.gestionetriage.model.Dottore;
import it.prova.gestionetriage.service.dottore.DottoreService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/dottore")
public class DottoreController {
	@Autowired
	private DottoreService dottoreService;
	@Autowired
	private WebClient webClient;
	
	@GetMapping
	public List<DottoreDTO> getAll() {
		return DottoreDTO.buildDottoreDTOListFromModelList(dottoreService.listAllElements(), false); // con paziente?
	}
	
	@GetMapping("/{id}")
	public DottoreDTO findById(@PathVariable(value = "id", required = true) long id) {
		Dottore dottore = dottoreService.caricaSingoloElemento(id); // con paziente?

		if (dottore == null)
			throw new DottoreNotFoundException("Dottore not found con id: " + id);

		return DottoreDTO.buildDottoreDTOFromModel(dottore, false); // true?
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DottoreDTO createNew(@Valid @RequestBody DottoreDTO dottoreInput) {
		//se mi viene inviato un id jpa lo interpreta come update ed a me (producer) non sta bene
		if(dottoreInput.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");
		
		// prima di salvarlo devo verificare se la banca dati esterna lo censisce
		ResponseEntity<DottoreDTO> response = webClient.post().uri("")
				.body(Mono.just(new DottoreDTO(dottoreInput.getNome(), dottoreInput.getCognome(),
						dottoreInput.getCodiceDipendente())), DottoreDTO.class)
				.retrieve().toEntity(DottoreDTO.class).block();

		// ANDREBBE GESTITA CON ADVICE!!!
		if (response.getStatusCode() != HttpStatus.CREATED) 
			throw new RuntimeException("Errore nella creazione della nuova voce tramite api esterna!!!");
	
		
		Dottore dottoreInserito = dottoreService.inserisciNuovo(dottoreInput.buildDottoreModel());
		
		//System.out.println(AirbusDTO.buildAirbusDTOFromModel(airbusInserito, false));
		
		return DottoreDTO.buildDottoreDTOFromModel(dottoreInserito, false);
	}
	
	
	
	

	@DeleteMapping("/{id}")
	public void delete(@PathVariable(value = "id", required = true) long id) {
		Dottore dottore = dottoreService.caricaSingoloElemento(id);

		if (dottore == null)
			throw new DottoreNotFoundException("Dottore not found con id: " + id);
		
		if (dottore.getPazienteAttualmenteInVisita() != null && dottore.getPazienteAttualmenteInVisita().getId() != null)
			throw new DottoreNotEmptyException("Dottore with id: " + id + " has a patient associated, you cannot delete it");
		
		// il block significa agire in maniera sincrona, attendendo la risposta
		webClient.delete().uri("/" + id)
				.retrieve().bodyToMono(DottoreDTO.class).block();

		
		dottoreService.rimuovi(dottore);
	}
	
	
	/*
	@PutMapping("/{id}")
	public DottoreDTO update(@Valid @RequestBody DottoreDTO dottoreInput, @PathVariable(required = true) Long id) {
		Dottore dottore = dottoreService.caricaSingoloElemento(id);

		if (dottore == null)
			throw new DottoreNotFoundException("Not not found con id: " + id);

		dottoreInput.setId(id);
		Dottore dottoreAggiornato = dottoreService.aggiorna(dottoreInput.buildDottoreModel());
		return DottoreDTO.buildDottoreDTOFromModel(dottoreAggiornato, false);
	}
	
	
	@PostMapping("/search")
	public List<DottoreDTO> search(@RequestBody DottoreDTO example) {
		return DottoreDTO.buildDottoreDTOListFromModelList(
				dottoreService.findByExample(example.buildDottoreModel(), null, null, null).toList(), false);
	}
	*/
	
}
