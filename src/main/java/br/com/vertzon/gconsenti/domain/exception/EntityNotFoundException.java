package br.com.vertzon.gconsenti.domain.exception;

public class EntityNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1889565258519300458L;

	public EntityNotFoundException(String message) {
		super(message);
	}

}
