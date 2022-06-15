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
import br.com.vertzon.gconsenti.domain.model.IdentificationColumn;
import br.com.vertzon.gconsenti.domain.repository.ErrorMessageRepository;
import br.com.vertzon.gconsenti.domain.repository.IdentificationColumnRepository;

@Service
public class IdentificationColumnService {

	@Autowired
	private IdentificationColumnRepository identificationColumnRepository;
	@Autowired
	private ErrorMessageRepository errorMessagesRepository;

	@Transactional
	public IdentificationColumn register(IdentificationColumn identificationColumn) {
		if (StringUtils.isEmpty(identificationColumn.getColumnName())) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0104"));
		}
		return identificationColumnRepository.saveAndFlush(identificationColumn);
	}

	@Transactional
	public void delete(Long id) {
		try {
			identificationColumnRepository.deleteById(id);
			identificationColumnRepository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0100"), id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0101"), id));
		}
	}

	@Transactional(readOnly = true)
	public List<IdentificationColumn> search(IdentificationColumn identificationColumnFilter) {
		Example<IdentificationColumn> example = Example.of(identificationColumnFilter,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return identificationColumnRepository.findAll(example);
	}
}
