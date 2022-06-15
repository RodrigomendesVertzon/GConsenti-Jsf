package br.com.vertzon.gconsenti.domain.model.enumerator;

public enum ProfileEnum {
	ADMINISTRATOR("ADMINISTRATOR"),
	WEBMASTER("WEBMASTER"),
	AUDITOR("AUDITOR");

	private final String label;
	
	private ProfileEnum(String label) {
		this.label = label;
	}
	
	//GETTERS
	public String getLabel() {
		return label;
	}
	
	public static ProfileEnum getValueOfLabel(String label) {
	    for (ProfileEnum profileAccessEnumValue : values()) {
	        if (profileAccessEnumValue.label.equals(label)) {
	            return profileAccessEnumValue;
	        }
	    }
	    return null;
	}
}
