package br.com.vertzon.gconsenti.domain.model.enumerator;

public enum JSFBeanEnum {
	
	APP_CONFIGURATION_REG("App_Configuration_Reg"),
	DATABASE_TYPE_REG("Database_Type_Reg"),
	DATASOURCE_REG("Datasource_Reg"),
	FILESOURCE_REG("Filesource_Reg"),
	FINALITY_REG("Finality_Reg"),
	IDENTIFICATION_COLUMN_REG("Identification_Column_Reg"),
	IDENTIFICATION_FIELD_REG("Identification_Field_Reg"),
	LEARNING_DATA_REG("Learning_Data_Reg"),
	LEARNING_REGEX_REG("Learning_Regex_Reg"),
	LEARNING_SCRIPT_REG("Learning_Script_Reg"),
	LEGAL_BASE_FINALITY_REG("Legal_Base_Finality_Reg"),
//	LEGAL_BASE,
//	PERSONAL_DATA_LOCATION,
	PERSONAL_DATA_TYPE_REG("Personal_Data_Type_Reg"),
	SCAN_SCHEDULE_DATA_REG("Scan_Schedule_Data_Reg"),
	SCAN_SCHEDULE_FILE_REG("Scan_Schedule_File_Reg"),
	USER_REG("User_Reg"),
	ERROR_MESSAGE_REG("Error_Message_Reg"),
	JSF_LABEL_REG("Jsf_Label_Reg"),
	
	GENERIC_SRC("Generic_Src"),
	APP_CONFIGURATION_SRC("App_Configuration_Src"),
	DATABASE_TYPE_SRC("Database_Type_Src"),
	DATASOURCE_SRC("Datasource_Src"),
	FILESOURCE_SRC("Filesource_Src"),
	FINALITY_SRC("Finality_Src"),
	IDENTIFICATION_COLUMN_SRC("Identification_Column_Src"),
	IDENTIFICATION_FIELD_SRC("Identification_Field_Src"),
	LEARNING_DATA_SRC("Learning_Data_Src"),
	LEARNING_REGEX_SRC("Learning_Regex_Src"),
	LEARNING_SCRIPT_SRC("Learning_Script_Src"),
	LEGAL_BASE_FINALITY_SRC("Legal_Base_Finality_Src"),
	LEGAL_BASE_SRC("Legal_Base_Src"),
	PERSONAL_DATA_TYPE_SRC("Personal_Data_Type_Src"),
	SCAN_SCHEDULE_SRC("Scan_Schedule_Data_Src"),
	USER_SRC("User_Src"),
	ERROR_MESSAGE_SRC("Error_Message_Src"),
	JSF_LABEL_SRC("Jsf_Label_Src");
	
	private final String label;

	private JSFBeanEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public static JSFBeanEnum getValueOfLabel(String label) {
	    for (JSFBeanEnum jsfBeanEnumValue : values()) {
	        if (jsfBeanEnumValue.label.equals(label)) {
	            return jsfBeanEnumValue;
	        }
	    }
	    return null;
	}
}
