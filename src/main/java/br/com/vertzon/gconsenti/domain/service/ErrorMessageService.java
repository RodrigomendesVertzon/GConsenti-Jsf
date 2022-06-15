package br.com.vertzon.gconsenti.domain.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vertzon.gconsenti.domain.exception.BusinessRuleException;
import br.com.vertzon.gconsenti.domain.model.ErrorMessage;
import br.com.vertzon.gconsenti.domain.repository.ErrorMessageRepository;

@Service
public class ErrorMessageService {

	@Autowired
	private ErrorMessageRepository errorMessageRepository;
	
	
	@Transactional
	public ErrorMessage register(ErrorMessage errorMessage) {
		
		if(StringUtils.isEmpty(errorMessage.getErrorCode())) {
			throw new BusinessRuleException(
					errorMessageRepository.findByErrorCode("gc-0148"));
		
		}if (StringUtils.isEmpty(errorMessage.getLabel())) {
			throw new BusinessRuleException(
					errorMessageRepository.findByErrorCode("gc-0149"));
		
		}if (StringUtils.isEmpty(errorMessage.getDescError()))
			throw new BusinessRuleException(
					errorMessageRepository.findByErrorCode("gc-0150"));
		
		return errorMessageRepository.saveAndFlush(errorMessage);
	}
	
	@Transactional(readOnly = true)
	public List<ErrorMessage> search(ErrorMessage errorMessageFilter) {
		Example<ErrorMessage> example = Example.of(errorMessageFilter,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return errorMessageRepository.findAll(example);
	}
}
