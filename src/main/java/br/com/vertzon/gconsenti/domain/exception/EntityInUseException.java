package br.com.vertzon.gconsenti.domain.exception;

public class EntityInUseException extends RuntimeException {
	
	private static final long serialVersionUID = 902897872666689180L;

	public EntityInUseException(String message) {
		super(message);
	}

}
