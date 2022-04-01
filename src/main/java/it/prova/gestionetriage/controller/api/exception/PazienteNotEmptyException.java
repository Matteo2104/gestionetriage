package it.prova.gestionetriage.controller.api.exception;

public class PazienteNotEmptyException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PazienteNotEmptyException(String message) {
		super(message);
	}
}
