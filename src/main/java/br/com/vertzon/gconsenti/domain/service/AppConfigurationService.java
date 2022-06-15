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
import org.springframework.web.bind.annotation.PathVariable;

import br.com.vertzon.gconsenti.domain.exception.BusinessRuleException;
import br.com.vertzon.gconsenti.domain.exception.EntityInUseException;
import br.com.vertzon.gconsenti.domain.exception.EntityNotFoundException;
import br.com.vertzon.gconsenti.domain.model.AppConfiguration;
import br.com.vertzon.gconsenti.domain.repository.AppConfigurationRepository;
import br.com.vertzon.gconsenti.domain.repository.ErrorMessageRepository;

@Service
public class AppConfigurationService {


	@Autowired
	private AppConfigurationRepository appConfigurationRepository;
	@Autowired
	private ErrorMessageRepository errorMessagesRepository;
	
	@Transactional
	public AppConfiguration register(AppConfiguration appConfiguration) {
		if (StringUtils.isEmpty(appConfiguration.getLabel())) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0104"));
		
		}if (StringUtils.isEmpty(appConfiguration.getValue())) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0103"));
		
		}return appConfigurationRepository.saveAndFlush(appConfiguration);
	}

	@Transactional
	public void delete(@PathVariable Long id ) {
		try {
			appConfigurationRepository.deleteById(id);
			appConfigurationRepository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0100"), id));
		
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0101"), id));
		}
	}

	@Transactional(readOnly = true)
	public List<AppConfiguration> search(AppConfiguration appConfigurationFilter) {
		Example<AppConfiguration> example = Example.of(appConfigurationFilter,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return appConfigurationRepository.findAll(example);
	}
}
