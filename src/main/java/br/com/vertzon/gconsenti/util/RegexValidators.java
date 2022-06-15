package br.com.vertzon.gconsenti.util;

public class RegexValidators {

	private static final String passwordRegex = "^(?=.*?\\p{Lu})(?=.*?\\p{Ll})(?=.*?\\d)" +
		    "(?=.*?[`~!@#$%^&*()\\-_=+\\\\|\\[{\\]};:'\",<.>/?]).*$";
	
	private static final String emailRegex = "";
	
	
	
	public static final String getPasswordRegex() {
		return passwordRegex;
	}
	
	public static final String getEmailRegex() {
		return emailRegex;
	}
}
