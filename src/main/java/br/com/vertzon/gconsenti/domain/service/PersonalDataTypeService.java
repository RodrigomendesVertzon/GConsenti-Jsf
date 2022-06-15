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
import br.com.vertzon.gconsenti.domain.model.PersonalDataType;
import br.com.vertzon.gconsenti.domain.repository.ErrorMessageRepository;
import br.com.vertzon.gconsenti.domain.repository.PersonalDataTypeRepository;

@Service
public class PersonalDataTypeService {

	@Autowired
	private PersonalDataTypeRepository personalDataTypeRepository;
	@Autowired
	private ErrorMessageRepository errorMessagesRepository;

	@Transactional
	public PersonalDataType register(PersonalDataType personalDataType) {
		if (StringUtils.isEmpty(personalDataType.getName())) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0104"));
		}

		if (StringUtils.isEmpty(personalDataType.getMethod().getLabel())) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0136"));
		}
		
		if (StringUtils.isEmpty(personalDataType.getCategory().getLabel())) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0137"));
		}
		return personalDataTypeRepository.saveAndFlush(personalDataType);
	}

	@Transactional
	public void delete(Long id) {
		try {
			personalDataTypeRepository.deleteById(id);
			personalDataTypeRepository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0100"), id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0101"), id));
		}
	}

	@Transactional(readOnly = true)
	public List<PersonalDataType> search(PersonalDataType personalDataTypeFilter) {
		Example<PersonalDataType> example = Example.of(personalDataTypeFilter,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return personalDataTypeRepository.findAll(example);
	}
}
