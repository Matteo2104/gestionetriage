package it.prova.gestionetriage.controller.api.exception;

public class DottoreNotEmptyException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DottoreNotEmptyException(String message) {
		super(message);
	}
}
