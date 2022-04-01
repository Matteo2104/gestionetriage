package it.prova.gestionetriage.controller.api.exception;

public class PazienteNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PazienteNotFoundException(String message) {
		super(message);
	}

}
