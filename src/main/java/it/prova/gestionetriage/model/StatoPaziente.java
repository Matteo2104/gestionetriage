package it.prova.gestionetriage.model;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;


public enum StatoPaziente {
	IN_ATTESA_VISITA, IN_VISITA, RICOVERATO, DIMESSO;
	
	@JsonCreator
	public static StatoPaziente getStatoPazienteFromCode(String input) {
		if(StringUtils.isBlank(input))
			return null;
		
		for (StatoPaziente statoPazienteItem : StatoPaziente.values()) {
			if (statoPazienteItem.equals(StatoPaziente.valueOf(input))) {
				return statoPazienteItem;
			}
		}
		return null;
	}
}
