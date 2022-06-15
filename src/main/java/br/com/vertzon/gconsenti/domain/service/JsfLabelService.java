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
import br.com.vertzon.gconsenti.domain.model.JsfLabel;
import br.com.vertzon.gconsenti.domain.repository.ErrorMessageRepository;
import br.com.vertzon.gconsenti.domain.repository.JsfLabelRepository;

@Service 
public class JsfLabelService {
	
	@Autowired
	private JsfLabelRepository jsfLabelRepository;
	@Autowired
	private ErrorMessageRepository errorMessageRepository;

	@Transactional
	public JsfLabel register(JsfLabel jsfLabel) {
		
		if (StringUtils.isEmpty(jsfLabel.getJsfCode())) {
			throw new BusinessRuleException(
					errorMessageRepository.findByErrorCode("gc-0151"));
	
		}if (jsfLabel.getJsfBean() == null) {
			throw new BusinessRuleException(
					errorMessageRepository.findByErrorCode("gc-0152"));
		
		}if (StringUtils.isEmpty(jsfLabel.getLabel())) {
			throw new BusinessRuleException(
					errorMessageRepository.findByErrorCode("gc-0153"));
		
		}
		return jsfLabelRepository.saveAndFlush(jsfLabel);
	}
		
	@Transactional(readOnly = true)
	public List<JsfLabel> search (JsfLabel jsfLabelFilter) {
		Example<JsfLabel> example = Example.of(jsfLabelFilter,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return jsfLabelRepository.findAll(example);
	}
}
