package br.com.vertzon.gconsenti.domain.model.correlation;

import org.springframework.security.core.Transient;
import org.springframework.stereotype.Component;

@Component
@Transient
public interface PersonalDataLocationView {

	String getId();
	String getDatasource_name();
	String getTable_name();
	String getColumn_name();
	String getFirst_factor();
	String getSecond_factor();
	String getThird_factor();
	
}
