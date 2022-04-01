package it.prova.gestionetriage.controller.api.exception;

public class AirbusNotNullForInsertException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AirbusNotNullForInsertException(String message) {
		super(message);
	}
}
