package br.org.serratec.apig4.exception;

public class EmailException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public EmailException(String message) {
		super(message);
	}
	
}
