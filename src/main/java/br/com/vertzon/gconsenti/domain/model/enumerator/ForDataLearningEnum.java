package br.com.vertzon.gconsenti.domain.model.enumerator;

public enum ForDataLearningEnum {

	NOT_DATA_LEARNING("Não"),
	DATA_LEARNING("Sim");
	
	
	
	private final String label;
	
	private ForDataLearningEnum(String label) {
		this.label = label;
	}

	//GETTERS
	public String getLabel() {
		return label;
	}
	
	public static ForDataLearningEnum getValueOfLabel(String label) {
	    for (ForDataLearningEnum dataLearningEnumValue : values()) {
	        if (dataLearningEnumValue.label.equals(label)) {
	            return dataLearningEnumValue;
	        }
	    }
	    return null;
	}
}
