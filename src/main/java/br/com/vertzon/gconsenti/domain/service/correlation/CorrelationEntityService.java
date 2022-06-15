package br.com.vertzon.gconsenti.domain.service.correlation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vertzon.gconsenti.domain.model.PersonalDataLocation;
import br.com.vertzon.gconsenti.domain.model.correlation.CorrelationDTO;
import br.com.vertzon.gconsenti.domain.model.correlation.CorrelationEntity;
import br.com.vertzon.gconsenti.domain.model.correlation.CorrelationFactor;
import br.com.vertzon.gconsenti.domain.repository.PersonalDataLocationRepository;
import br.com.vertzon.gconsenti.domain.repository.correlation.CorrelationEntityRepository;

@Service
public class CorrelationEntityService {
	
	@Autowired
	private CorrelationEntityRepository correlationEntityRepository;
	
	@Autowired
	private PersonalDataLocationRepository personalDataLocationRepository;
	
	public CorrelationEntity correlateFactors(CorrelationFactor correlationFactor, CorrelationDTO correlationDTO) {
		CorrelationEntity correlationEntity = correlationEntityRepository.findCorrelationEntityByCorrelationFactor(
			correlationFactor,
			correlationDTO.getColumnName(),
			correlationDTO.getTableName(),
			correlationDTO.getDatasourceName()
		);
		
		if (correlationEntity == null) {
			correlationEntity = new CorrelationEntity();
			PersonalDataLocation personalDataLocation = personalDataLocationRepository.findById(Long.valueOf(correlationDTO.getPersonalDataLocationId())).get();
			correlationEntity.setCorrelationFactor(correlationFactor);
			correlationEntity.setPersonalDataLocation(personalDataLocation);
			return correlationEntityRepository.save(correlationEntity);
		}
		return null;
	}
	
	public CorrelationEntity correlateFactors(CorrelationFactor correlationFactor, Long personalDataLocationId) {
		PersonalDataLocation personalDataLocation = personalDataLocationRepository.findById(personalDataLocationId).get();
		
		CorrelationEntity correlationEntity = new CorrelationEntity();
		correlationEntity.setCorrelationFactor(correlationFactor);
		correlationEntity.setPersonalDataLocation(personalDataLocation);
		
		return correlationEntityRepository.save(correlationEntity);
	}
}
