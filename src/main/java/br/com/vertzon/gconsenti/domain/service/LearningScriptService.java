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

import com.microsoft.sqlserver.jdbc.StringUtils;

import br.com.vertzon.gconsenti.domain.exception.BusinessRuleException;
import br.com.vertzon.gconsenti.domain.exception.EntityInUseException;
import br.com.vertzon.gconsenti.domain.exception.EntityNotFoundException;
import br.com.vertzon.gconsenti.domain.model.LearningScript;
import br.com.vertzon.gconsenti.domain.repository.ErrorMessageRepository;
import br.com.vertzon.gconsenti.domain.repository.LearningScriptRepository;
import br.com.vertzon.gconsenti.domain.repository.PersonalDataTypeRepository;

@Service
public class LearningScriptService {

	@Autowired
	private LearningScriptRepository learningScriptRepository;
	@Autowired
	private PersonalDataTypeRepository personalDataTypeRepository;
	@Autowired
	private ErrorMessageRepository errorMessagesRepository;

	@Transactional
	public LearningScript register(LearningScript learningScript) {
		if (StringUtils.isEmpty(learningScript.getName())) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0104"));
		}

		if (StringUtils.isEmpty(learningScript.getCode())) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0131"));
		}
		
		if (!personalDataTypeRepository.findById(learningScript.getPersonalDataType().getId()).isPresent()) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0126"));
		}
		
		learningScriptRepository.findAll().forEach(scriptFound -> {
			if (scriptFound.getCode().equals(learningScript.getCode())) {
				throw new BusinessRuleException(
						errorMessagesRepository.findByErrorCode("gc-0132"));
			}
			
			if (scriptFound.getPersonalDataType().getId().equals(learningScript.getPersonalDataType().getId())) {
				throw new BusinessRuleException(
						errorMessagesRepository.findByErrorCode("gc-0130"));
			}
		});
		return learningScriptRepository.saveAndFlush(learningScript);
	}

	@Transactional
	public void delete(Long id) {
		try {
			learningScriptRepository.deleteById(id);
			learningScriptRepository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0100"), id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0101"), id));
		}
	}

	@Transactional(readOnly = true)
	public List<LearningScript> search(LearningScript learningScriptFilter) {
		Example<LearningScript> example = Example.of(learningScriptFilter,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return learningScriptRepository.findAll(example);
	}

}
