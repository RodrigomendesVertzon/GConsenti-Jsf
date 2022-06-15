package br.com.vertzon.gconsenti.domain.model.correlation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.vertzon.gconsenti.domain.model.PersonalDataLocation;

@Entity
@Table(name = "correlation_entities")
public class CorrelationEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	@JoinColumn(name = "personal_data_location_id")
	private PersonalDataLocation personalDataLocation;
	@OneToOne
	@JoinColumn(name = "correlation_factor_id")
	private CorrelationFactor correlationFactor;
	
	public PersonalDataLocation getPersonalDataLocation() {
		return personalDataLocation;
	}
	public void setPersonalDataLocation(PersonalDataLocation personalDataLocation) {
		this.personalDataLocation = personalDataLocation;
	}
	
	public CorrelationFactor getCorrelationFactor() {
		return correlationFactor;
	}
	public void setCorrelationFactor(CorrelationFactor correlationFactor) {
		this.correlationFactor = correlationFactor;
	}
}
