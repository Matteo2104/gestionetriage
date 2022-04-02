package it.prova.gestionetriage.controller.api.exception;

public class DottoreNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DottoreNotFoundException(String message) {
		super(message);
	}
}
