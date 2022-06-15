package br.com.vertzon.gconsenti.domain.service;

import java.util.List;

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
import br.com.vertzon.gconsenti.domain.model.LearningRegex;
import br.com.vertzon.gconsenti.domain.repository.ErrorMessageRepository;
import br.com.vertzon.gconsenti.domain.repository.LearningRegexRepository;
import br.com.vertzon.gconsenti.domain.repository.PersonalDataTypeRepository;

@Service
public class LearningRegexService {
	
	@Autowired
	private LearningRegexRepository learningRegexRepository;
	@Autowired
	private PersonalDataTypeRepository personalDataTypeRepository;
	@Autowired
	private ErrorMessageRepository errorMessagesRepository;

	@Transactional
	public LearningRegex register(LearningRegex learningRegex) {
		if (learningRegex.getName().isEmpty()) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0104"));
		}
		
		if (learningRegex.getPattern().isEmpty()) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0128"));
		}
		
		if (!personalDataTypeRepository.findById(learningRegex.getPersonalDataType().getId()).isPresent()) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0126"));
		}
		learningRegexRepository.findAll().forEach(regexFound -> {
			if (regexFound.getPattern().equals(learningRegex.getPattern())) {
				throw new BusinessRuleException(
						errorMessagesRepository.findByErrorCode("gc-0129"));
			}
			
			if (regexFound.getPersonalDataType().getId().equals(learningRegex.getPersonalDataType().getId())) {
				throw new BusinessRuleException(
						errorMessagesRepository.findByErrorCode("gc-0130"));
			}
		});
		return learningRegexRepository.saveAndFlush(learningRegex);
	}

	@Transactional
	public void delete(Long id) {
		try {
			learningRegexRepository.deleteById(id);
			learningRegexRepository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0100"), id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0101"), id));
		}
	}

	@Transactional(readOnly = true)
	public List<LearningRegex> search(LearningRegex learningRegexFilter) {
		Example<LearningRegex> example = Example.of(learningRegexFilter,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return learningRegexRepository.findAll(example);
	}

	public String getPatternByPersonalDataType(Long id) {
		return learningRegexRepository.findPatternByPersonalDataType(id);
	}
}
