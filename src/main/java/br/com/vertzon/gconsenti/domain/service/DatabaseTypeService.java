package br.com.vertzon.gconsenti.domain.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vertzon.gconsenti.domain.exception.BusinessRuleException;
import br.com.vertzon.gconsenti.domain.exception.EntityInUseException;
import br.com.vertzon.gconsenti.domain.exception.EntityNotFoundException;
import br.com.vertzon.gconsenti.domain.model.DatabaseType;
import br.com.vertzon.gconsenti.domain.repository.DatabaseTypeRepository;
import br.com.vertzon.gconsenti.domain.repository.ErrorMessageRepository;

@Service
public class DatabaseTypeService {

	@Autowired
	private DatabaseTypeRepository databaseTypeRepository;
	@Autowired
	private ErrorMessageRepository errorMessagesRepository;

	@Transactional
	public DatabaseType register(DatabaseType databaseType) {
		if (StringUtils.isEmpty(databaseType.getName())) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0104"));
		}
		
		if (StringUtils.isEmpty(databaseType.getDriver())) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0105"));
		}
		
		if (StringUtils.isEmpty(databaseType.getPrefix())) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0106"));
		}
		
		return databaseTypeRepository.saveAndFlush(databaseType);
	}

	@Transactional
	public void delete (Long id) {
		try {
			databaseTypeRepository.deleteById(id);
			databaseTypeRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0100"), id));
		
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0101"), id));
		}
	}

	@Transactional(readOnly = true)
	public List<DatabaseType> search(DatabaseType databaseTypeFilter) {
		Example<DatabaseType> example = Example.of(databaseTypeFilter,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return databaseTypeRepository.findAll(example);
	}
}
