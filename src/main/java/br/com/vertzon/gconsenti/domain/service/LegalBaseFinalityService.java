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
import br.com.vertzon.gconsenti.domain.model.LegalBaseFinality;
import br.com.vertzon.gconsenti.domain.repository.ErrorMessageRepository;
import br.com.vertzon.gconsenti.domain.repository.FinalityRepository;
import br.com.vertzon.gconsenti.domain.repository.LegalBaseFinalityRepository;
import br.com.vertzon.gconsenti.domain.repository.LegalBaseRepository;

@Service
public class LegalBaseFinalityService {

	@Autowired
	private LegalBaseRepository legalBaseRepository;
	@Autowired
	private FinalityRepository finalityRepository;
	@Autowired
	private LegalBaseFinalityRepository legalBaseFinalityRepository;
	@Autowired
	private ErrorMessageRepository errorMessagesRepository;

	@Transactional
	public LegalBaseFinality register(LegalBaseFinality legalBaseFinality) {
		if (!legalBaseRepository.findById(legalBaseFinality.getLegalBase().getId()).isPresent()) {
			throw new BusinessRuleException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0133"),
							legalBaseFinality.getLegalBase().getId()));
		}
		
		if (!finalityRepository.findById(legalBaseFinality.getFinality().getId()).isPresent()) {
			throw new BusinessRuleException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0134")
							, legalBaseFinality.getFinality().getId()));
		}
		return legalBaseFinalityRepository.saveAndFlush(legalBaseFinality);
	}

	@Transactional
	public void delete(Long id) {
		try {
			legalBaseFinalityRepository.deleteById(id);
			legalBaseFinalityRepository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0100"), id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0101"), id));
		}
	}

	@Transactional(readOnly = true)
	public List<LegalBaseFinality> search(LegalBaseFinality legalBaseFinalityFilter) {
		Example<LegalBaseFinality> example = Example.of(legalBaseFinalityFilter,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return legalBaseFinalityRepository.findAll(example);
	}
}
