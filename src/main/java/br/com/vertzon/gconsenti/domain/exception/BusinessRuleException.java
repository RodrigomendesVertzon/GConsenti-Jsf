package br.com.vertzon.gconsenti.domain.exception;

public class BusinessRuleException extends RuntimeException {
	
	private static final long serialVersionUID = 3251157859838911298L;

	public BusinessRuleException(String message) {
		super(message);
	}
}
