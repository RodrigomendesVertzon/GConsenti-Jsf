package br.com.vertzon.gconsenti.domain.model.correlation;

import org.springframework.security.core.Transient;

@Transient
public class OtherCorrelationDTO {
	
	private String oValue;
	private String personalDataLocationId;
	
	public String getOValue() {
		return oValue;
	}
	public void setOValue(String oValue) {
		this.oValue = oValue;
	}
	
	public String getPersonalDataLocationId() {
		return personalDataLocationId;
	}
	public void setPersonalDataLocationId(String personalDataLocationId) {
		this.personalDataLocationId = personalDataLocationId;
	}
}
