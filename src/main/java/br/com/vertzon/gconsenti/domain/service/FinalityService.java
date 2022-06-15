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
import br.com.vertzon.gconsenti.domain.model.Finality;
import br.com.vertzon.gconsenti.domain.repository.ErrorMessageRepository;
import br.com.vertzon.gconsenti.domain.repository.FinalityRepository;

@Service
public class FinalityService {

	@Autowired
	private FinalityRepository finalityRepository;
	@Autowired
	private ErrorMessageRepository errorMessagesRepository;

	@Transactional
	public Finality register(Finality finality) {
		if (StringUtils.isEmpty(finality.getName())) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0104"));
		}
		
		if (StringUtils.isEmpty(finality.getDescription())) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0119"));
		}
		
		finalityRepository.findAll().forEach(finalitiesFound -> {
			if (finalitiesFound.getName().equals(finality.getName())) {
				throw new BusinessRuleException(
						errorMessagesRepository.findByErrorCode("gc-0118"));
			}
		});
		return finalityRepository.saveAndFlush(finality);
	}

	@Transactional
	public void delete(Long id) {
		try {
			finalityRepository.deleteById(id);
			finalityRepository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0100"), id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0101"), id));
		}
	}

	@Transactional(readOnly = true)
	public List<Finality> search(Finality motivationFilter) {
		Example<Finality> example = Example.of(motivationFilter,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return finalityRepository.findAll(example);
	}
}
