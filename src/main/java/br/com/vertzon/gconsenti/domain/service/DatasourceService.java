package br.com.vertzon.gconsenti.domain.service;

import java.util.List;
import java.util.Objects;
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
import br.com.vertzon.gconsenti.domain.model.Datasource;
import br.com.vertzon.gconsenti.domain.model.LegalBaseFinality;
import br.com.vertzon.gconsenti.domain.repository.DatabaseTypeRepository;
import br.com.vertzon.gconsenti.domain.repository.DatasourceRepository;
import br.com.vertzon.gconsenti.domain.repository.ErrorMessageRepository;
import br.com.vertzon.gconsenti.domain.repository.LegalBaseFinalityRepository;

@Service
public class DatasourceService {

	@Autowired
	private DatasourceRepository datasourceRepository;
	@Autowired
	private DatabaseTypeRepository databaseTypeRepository;
	@Autowired
	private LegalBaseFinalityRepository legalBaseFinalityRepository;
	@Autowired
	private ErrorMessageRepository errorMessagesRepository;

	@Transactional
	public Datasource register(Datasource datasource) {
		if (!databaseTypeRepository.findById(datasource.getDatabaseType().getId()).isPresent()) {
			throw new BusinessRuleException(errorMessagesRepository.findByErrorCode("gc-0107"));
		}
		
		if (StringUtils.isEmpty(datasource.getIp())) {
			throw new BusinessRuleException(errorMessagesRepository.findByErrorCode("gc-0108"));
		}
		
		if (StringUtils.isEmpty(datasource.getPort())) {
			throw new BusinessRuleException(errorMessagesRepository.findByErrorCode("gc-0109"));
		}
		
		if (StringUtils.isEmpty(datasource.getName())) {
			throw new BusinessRuleException(errorMessagesRepository.findByErrorCode("gc-0104"));
		}
		
		if (StringUtils.isEmpty(datasource.getUsername())) {
			throw new BusinessRuleException(errorMessagesRepository.findByErrorCode("gc-0111"));
		}
		
		if (StringUtils.isEmpty(datasource.getPassword())) {
			throw new BusinessRuleException(errorMessagesRepository.findByErrorCode("gc-0112"));
		}
		
		datasource.getLegalBaseFinalities().forEach(legalBaseFinality -> {
			Optional<LegalBaseFinality> legalBaseFinalityToSearch = legalBaseFinalityRepository
					.findById(legalBaseFinality.getId());
			if (!legalBaseFinalityToSearch.isPresent()) {
				throw new BusinessRuleException(
						errorMessagesRepository.findByErrorCode("gc-0113"));
			}
		});
		
		if (StringUtils.isEmpty(datasource.getUrl())) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0114"));
		}
		
		//TODO fix exception when sending erroneous enumerator on DatasourceService
		if(Objects.isNull(datasource.getDataLearning().getLabel())) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0115"));
		}
		
		datasourceRepository.findAll().forEach(datasourceFound -> {
			if (!datasource.isEditing() && (datasource.getName().equals(datasourceFound.getName()))) {
				throw new BusinessRuleException(
						errorMessagesRepository.findByErrorCode("gc-0116"));
			}
		});
		return datasourceRepository.saveAndFlush(datasource);
	}

	@Transactional
	public void delete(Long id) {
		try {
			datasourceRepository.deleteById(id);
			datasourceRepository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0100"), id));
		
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0101"), id));
		}
	}

	@Transactional(readOnly = true)
	public List<Datasource> search(Datasource datasourceFilter) {
		Example<Datasource> example = Example.of(datasourceFilter,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return datasourceRepository.findAll(example);
	}
}
