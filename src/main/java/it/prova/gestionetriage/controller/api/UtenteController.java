package it.prova.gestionetriage.controller.api;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import it.prova.gestionetriage.controller.api.exception.IdNotNullForInsertException;
import it.prova.gestionetriage.controller.api.exception.UtenteNotFoundException;
import it.prova.gestionetriage.dto.UtenteDTO;
import it.prova.gestionetriage.model.Utente;
import it.prova.gestionetriage.service.utente.UtenteService;

@RestController
@RequestMapping("api/utente")
public class UtenteController {
	@Autowired
	private UtenteService utenteService;
	
	
	@GetMapping
	public List<UtenteDTO> getAll() {
		return UtenteDTO.createUtenteDTOListFromModelList(utenteService.listAllElementsEager(), true);
	}
	
	@GetMapping("/{id}")
	public UtenteDTO findById(@PathVariable(value = "id", required = true) long id) {
		Utente utente = utenteService.caricaSingoloElementoEager(id);

		if (utente == null)
			throw new UtenteNotFoundException("Utente not found con id: " + id);

		return UtenteDTO.buildUtenteDTOFromModel(utente, true); // true?
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UtenteDTO createNew(@Valid @RequestBody UtenteDTO utenteInput) {
		//se mi viene inviato un id jpa lo interpreta come update ed a me (producer) non sta bene
		if(utenteInput.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");
		
		Utente utenteInserito = utenteService.inserisciNuovo(utenteInput.buildUtenteModel());
		
		//System.out.println(AirbusDTO.buildAirbusDTOFromModel(airbusInserito, false));
		
		return UtenteDTO.buildUtenteDTOFromModel(utenteInserito, true);  // con ruoli ?
	}
	
	
	@PutMapping("/{id}")
	public UtenteDTO update(@Valid @RequestBody UtenteDTO utenteInput, @PathVariable(required = true) Long id) {
		Utente utente = utenteService.caricaSingoloElemento(id);

		if (utente == null)
			throw new UtenteNotFoundException("Utente not found con id: " + id);

		utenteInput.setId(id);
		Utente utenteAggiornato = utenteService.aggiorna(utenteInput.buildUtenteModel());
		
		return UtenteDTO.buildUtenteDTOFromModel(utenteAggiornato, true); // con ruoli ?
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(required = true) Long id) {
		Utente utente = utenteService.caricaSingoloElementoEager(id);

		if (utente == null)
			throw new UtenteNotFoundException("Utente not found con id: " + id);
		
		// come gestire la rimozione utente ???
	
		//pazienteService.rimuovi(paziente);
	}
	
	
	@PostMapping("/search")
	public List<UtenteDTO> search(@RequestBody UtenteDTO example) {
		return UtenteDTO.createUtenteDTOListFromModelList(
				utenteService.findByExample(example.buildUtenteModel(), null, null, null).toList(), true);
	}
	
}
