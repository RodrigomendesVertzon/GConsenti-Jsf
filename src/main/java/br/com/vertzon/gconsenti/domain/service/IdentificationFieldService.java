package br.com.vertzon.gconsenti.domain.service;

import java.util.List;
import java.util.Optional;

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
import br.com.vertzon.gconsenti.domain.model.IdentificationField;
import br.com.vertzon.gconsenti.domain.repository.ErrorMessageRepository;
import br.com.vertzon.gconsenti.domain.repository.IdentificationColumnRepository;
import br.com.vertzon.gconsenti.domain.repository.IdentificationFieldRepository;
import br.com.vertzon.gconsenti.domain.repository.PersonalDataTypeRepository;

@Service
public class IdentificationFieldService {

	@Autowired
	private IdentificationFieldRepository identificationFieldRepository;
	@Autowired
	private IdentificationColumnRepository identificationColumnRepository;
	@Autowired
	private PersonalDataTypeRepository personalDataTypeRepository;
	@Autowired
	private ErrorMessageRepository errorMessagesRepository;

	@Transactional
	public IdentificationField register(IdentificationField identificationField) {
		if (StringUtils.isEmpty(identificationField.getName())) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0104"));
		}
		identificationField.getIdentificationColumns().forEach(identificationColumn -> {
			Optional<IdentificationColumn> identificationColumnToSearch =
					identificationColumnRepository.findById(identificationColumn.getId());
			if(!identificationColumnToSearch.isPresent()) {
				throw new BusinessRuleException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0120"),
							identificationColumn.getId())
				);
			}
		});
		
		if (!personalDataTypeRepository.findById(identificationField.getPersonalDataType().getId()).isPresent()) {
			throw new BusinessRuleException(
				String.format(errorMessagesRepository.findByErrorCode("gc-0121"),
						identificationField.getPersonalDataType().getId()));
		}
		return identificationFieldRepository.saveAndFlush(identificationField);
	}

	@Transactional
	public void delete(Long id) {
		try {
			identificationFieldRepository.deleteById(id);
			identificationFieldRepository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0100"), id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0101"), id));
		}
	}

	@Transactional(readOnly = true)
	public List<IdentificationField> search(IdentificationField identificationFieldFilter) {
		Example<IdentificationField> example = Example.of(identificationFieldFilter,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return identificationFieldRepository.findAll(example);
	}
}
