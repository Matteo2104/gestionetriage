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
import it.prova.gestionetriage.controller.api.exception.PazienteNotEmptyException;
import it.prova.gestionetriage.controller.api.exception.PazienteNotFoundException;
import it.prova.gestionetriage.dto.PazienteDTO;
import it.prova.gestionetriage.model.Paziente;
import it.prova.gestionetriage.service.paziente.PazienteService;
import it.prova.gestionetriage.service.utente.UtenteService;

@RestController
@RequestMapping("api/utente")
public class UtenteController {
	@Autowired
	private UtenteService utenteService;
	
	/*
	@GetMapping
	public List<PazienteDTO> getAll() {
		return PazienteDTO.buildPazienteDTOListFromModelList(pazienteService.listAllElements(), false); // con dottore?
	}
	
	@GetMapping("/{id}")
	public PazienteDTO findById(@PathVariable(value = "id", required = true) long id) {
		Paziente paziente = pazienteService.caricaSingoloElemento(id); // con dottore?

		if (paziente == null)
			throw new PazienteNotFoundException("Paziente not found con id: " + id);

		return PazienteDTO.buildPazienteDTOFromModel(paziente, false); // true?
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PazienteDTO createNew(@Valid @RequestBody PazienteDTO pazienteInput) {
		//se mi viene inviato un id jpa lo interpreta come update ed a me (producer) non sta bene
		if(pazienteInput.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");
		
		Paziente pazienteInserito = pazienteService.inserisciNuovo(pazienteInput.buildPazienteModel());
		
		//System.out.println(AirbusDTO.buildAirbusDTOFromModel(airbusInserito, false));
		
		return PazienteDTO.buildPazienteDTOFromModel(pazienteInserito, false);
	}
	
	
	@PutMapping("/{id}")
	public PazienteDTO update(@Valid @RequestBody PazienteDTO pazienteInput, @PathVariable(required = true) Long id) {
		Paziente paziente = pazienteService.caricaSingoloElemento(id);

		if (paziente == null)
			throw new PazienteNotFoundException("Paziente not found con id: " + id);

		pazienteInput.setId(id);
		Paziente pazienteAggiornato = pazienteService.aggiorna(pazienteInput.buildPazienteModel());
		return PazienteDTO.buildPazienteDTOFromModel(pazienteAggiornato, false);
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(required = true) Long id) {
		Paziente paziente = pazienteService.caricaSingoloElementoEager(id);

		if (paziente == null)
			throw new PazienteNotFoundException("Paziente not found con id: " + id);
		
		
		if (paziente.getDottore() != null)
			throw new PazienteNotEmptyException("Paziente with id: " + id + " has a doctor associated, you cannot delete it");
		
	
		pazienteService.rimuovi(paziente);
	}
	
	
	@PostMapping("/search")
	public List<PazienteDTO> search(@RequestBody PazienteDTO example) {
		return PazienteDTO.buildPazienteDTOListFromModelList(
				pazienteService.findByExample(example.buildPazienteModel(), null, null, null).toList(), false);
	}
	*/
}
