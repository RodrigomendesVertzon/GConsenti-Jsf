package br.com.vertzon.gconsenti.domain.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.vertzon.gconsenti.domain.exception.BusinessRuleException;
import br.com.vertzon.gconsenti.domain.exception.EntityInUseException;
import br.com.vertzon.gconsenti.domain.exception.EntityNotFoundException;
import br.com.vertzon.gconsenti.domain.model.PersonalDataLocation;
import br.com.vertzon.gconsenti.domain.model.correlation.CorrelationView;
import br.com.vertzon.gconsenti.domain.repository.ErrorMessageRepository;
import br.com.vertzon.gconsenti.domain.repository.PersonalDataLocationRepository;

@Service
public class PersonalDataLocationService {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PersonalDataLocationRepository personalDataLocationRepository;
	@Autowired
	private ErrorMessageRepository errorMessagesRepository;

	@Transactional
	public PersonalDataLocation register(PersonalDataLocation personalDataLocation) {
		if (org.apache.commons.lang3.StringUtils.isEmpty(personalDataLocation.getPersonalDataType())) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0135"));
		}
		
		if (org.apache.commons.lang3.StringUtils.isEmpty(personalDataLocation.getPersonalDataTypeName())) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-104"));
		}
		
		if (org.apache.commons.lang3.StringUtils.isEmpty(personalDataLocation.getIp())) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0108"));
		}
		
		return personalDataLocationRepository.saveAndFlush(personalDataLocation);
	}

	@Transactional
	public void delete(Long id) {
		try {
			personalDataLocationRepository.deleteById(id);
			personalDataLocationRepository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0100"), id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0101"), id));
		}
	}

	@Transactional(readOnly = true)
	public boolean checkIfAlreadyExists(String datasourceName, String tableName, String columnName,
			String personalDataTypeName) {
		return personalDataLocationRepository.alreadyExists(datasourceName, tableName, columnName,
				personalDataTypeName);
	}

	@Transactional(readOnly = true)
	public List<PersonalDataLocation> search(String datasourceName) {
		return personalDataLocationRepository.findByDatasourceName(datasourceName);
	}
	
	@SuppressWarnings("unchecked")
	public List<CorrelationView> getByNameOrRgOrCpf(String nome, String cpf, String rg) {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT DISTINCT ");
		jpql.append("pdl.datasource_name banco, ");
		jpql.append("pdl.table_name tabela, ");
		jpql.append("pdl.column_name coluna, ");
		jpql.append("CONCAT(nde.aid, nde.bid, nde.cid)nome ");
		jpql.append("FROM personal_data_locations pdl ");
		jpql.append("INNER JOIN correlation_entities ce ");
		jpql.append("ON pdl.id = ce.personal_data_location_id ");
		jpql.append("INNER JOIN correlation_factors cf ");
		jpql.append("ON cf.id = ce.correlation_factor_id ");
		jpql.append("FULL join c_data_entities cde ");
		jpql.append("ON cf.c_data_entity_id = cde.id ");
		jpql.append("FULL join r_data_entities rde ");
		jpql.append("ON cf.r_data_entity_id = rde.id ");
		jpql.append("FULL join n_data_entities nde ");
		jpql.append("ON cf.n_data_entity_id = nde.id ");
		jpql.append("WHERE 0 = 0 ");
		
		if(!nome.isEmpty() || nome != null) {
			jpql.append("AND CONCAT(nde.aid, nde.bid, nde.cid) like :nome ");
		}
		if(!cpf.isEmpty() || cpf != null) {
			jpql.append("AND CONCAT(cde.aid, cde.bid, cde.cid) like :cpf ");
		}
		if(!rg.isEmpty() || rg != null) {
			jpql.append("AND CONCAT(rde.aid, rde.bid, rde.cid) like :rg ");
		}
		
		if(StringUtils.hasLength(nome) || StringUtils.hasLength(cpf) || StringUtils.hasLength(rg)) { 
			return manager.createNativeQuery(jpql.toString(), "CorrelationView")
				.setParameter("nome", "%" + nome + "%")
				.setParameter("cpf", "%" + cpf + "%")
				.setParameter("rg", "%" + rg + "%")
			.getResultList();
		}
		return null;
	}
}
