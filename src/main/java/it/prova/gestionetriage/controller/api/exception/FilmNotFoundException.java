package it.prova.gestionetriage.controller.api.exception;

public class FilmNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FilmNotFoundException(String message) {
		super(message);
	}

}
