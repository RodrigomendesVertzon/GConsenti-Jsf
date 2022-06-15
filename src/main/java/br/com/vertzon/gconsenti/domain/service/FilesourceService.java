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
import br.com.vertzon.gconsenti.domain.model.Filesource;
import br.com.vertzon.gconsenti.domain.model.LegalBaseFinality;
import br.com.vertzon.gconsenti.domain.repository.ErrorMessageRepository;
import br.com.vertzon.gconsenti.domain.repository.FilesourceRepository;
import br.com.vertzon.gconsenti.domain.repository.LegalBaseFinalityRepository;

@Service
public class FilesourceService {

	@Autowired
	private FilesourceRepository filesourceRepository;
	@Autowired
	private LegalBaseFinalityRepository legalBaseFinalityRepository;
	@Autowired
	private ErrorMessageRepository errorMessagesRepository;

	@Transactional
	public Filesource register(Filesource filesource) {
		if (StringUtils.isEmpty(filesource.getName())) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0104"));
		}
		
		if (StringUtils.isEmpty(filesource.getFilePath())) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0102"));
		}
		
		filesource.getLegalBaseFinalities().forEach(legalBaseFinality -> {
			Optional<LegalBaseFinality> legalBaseFinalityToSearch = legalBaseFinalityRepository
					.findById(legalBaseFinality.getId());
			
			if (!legalBaseFinalityToSearch.isPresent()) {
				throw new BusinessRuleException(
						errorMessagesRepository.findByErrorCode("gc-110"));
			}
		});
		filesourceRepository.findAll().forEach(filesourceFound -> {
			if (!filesource.isEditing() && (filesourceFound.getFilePath().equals(filesource.getFilePath()))) {
				throw new BusinessRuleException(
						errorMessagesRepository.findByErrorCode("gc-0117"));
			}
		});
		return filesourceRepository.saveAndFlush(filesource);
	}

	@Transactional
	public void delete(Long id) {
		try {
			filesourceRepository.deleteById(id);
			filesourceRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0100"), id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0101"), id));
		}
	}

	@Transactional(readOnly = true)
	public List<Filesource> search(Filesource filesourceFilter) {
		Example<Filesource> example = Example.of(filesourceFilter,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return filesourceRepository.findAll(example);
	}
}
