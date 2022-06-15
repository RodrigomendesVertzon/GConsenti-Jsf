package br.com.vertzon.gconsenti.domain.model.enumerator;

public enum IdentificationMethodEnum {
	
	REGEX_IDENTIFICATION("Regex"),
	SCRIPT_IDENTIFICATION("Script"),
	AI_IDENTIFICATION("AI");
	
	private final String label;
	
	private IdentificationMethodEnum(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static IdentificationMethodEnum getValueOfLabel(String label) {
	    for (IdentificationMethodEnum identificationMethodEnumValue : values()) {
	        if (identificationMethodEnumValue.label.equals(label)) {
	            return identificationMethodEnumValue;
	        }
	    }
	    return null;
	}
	
}
