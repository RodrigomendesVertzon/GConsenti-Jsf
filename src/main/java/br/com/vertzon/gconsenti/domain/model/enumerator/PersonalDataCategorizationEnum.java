package br.com.vertzon.gconsenti.domain.model.enumerator;

public enum PersonalDataCategorizationEnum {
	DIRETO("Direto"),
	INDIRETO("Indireto"),
	SENSIVEL("Sensível");

	private final String label;
	
	private PersonalDataCategorizationEnum(String label) {
		this.label = label;
	}
	
	//GETTERS
	public String getLabel() {
		return label;
	}
	
	public static PersonalDataCategorizationEnum getValueOfLabel(String label) {
	    for (PersonalDataCategorizationEnum personalDataCategorizationEnumValue : values()) {
	        if (personalDataCategorizationEnumValue.label.equals(label)) {
	            return personalDataCategorizationEnumValue;
	        }
	    }
	    return null;
	}
}
