package br.com.hearthstone.domain.validation;

public class ValidationException extends Exception{

	private static final long serialVersionUID = 8704580157381710800L;
	
	public ValidationException(String message) {
		super(message);
	}

}
