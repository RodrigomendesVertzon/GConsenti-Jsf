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
import br.com.vertzon.gconsenti.domain.model.LearningData;
import br.com.vertzon.gconsenti.domain.repository.DatasourceRepository;
import br.com.vertzon.gconsenti.domain.repository.ErrorMessageRepository;
import br.com.vertzon.gconsenti.domain.repository.LearningDataRepository;
import br.com.vertzon.gconsenti.domain.repository.PersonalDataTypeRepository;

@Service
public class LearningDataService {
	
	@Autowired
	private DatasourceRepository datasourceRepository;
	@Autowired
	private PersonalDataTypeRepository personalDataTypeRepository;
	@Autowired
	private LearningDataRepository learningDataRepository;
	@Autowired
	private ErrorMessageRepository errorMessagesRepository;

	@Transactional
	public LearningData register(LearningData learningData) {
		if (StringUtils.isEmpty(learningData.getName())) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0104"));
		}
		
		if (StringUtils.isEmpty(learningData.getTableName())) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0122"));
		}
		
		if (StringUtils.isEmpty(learningData.getDecisionColumn())) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0123"));
		}
		
		if (!datasourceRepository.findById(learningData.getDatasource().getId()).isPresent()) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0124"));
		}
		
		if (datasourceRepository.findById(learningData.getDatasource().getId()).get().getDataLearning().getLabel()
				.equals("Não")) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0125"));
		}
		
		if (!personalDataTypeRepository.findById(learningData.getPersonalDataType().getId()).isPresent()) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0126"));
		}
		
		learningDataRepository.findAll().forEach(dataFound -> {
			if (!learningData.isEditing() && (dataFound.getDatasource().equals(learningData.getDatasource()))) {
				throw new BusinessRuleException(
						errorMessagesRepository.findByErrorCode("gc-0127"));
			}
		});
		return learningDataRepository.saveAndFlush(learningData);
	}

	@Transactional
	public void delete(Long id) {
		try {
			learningDataRepository.deleteById(id);
			learningDataRepository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0100"), id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0101"), id));
		}
	}

	@Transactional(readOnly = true)
	public List<LearningData> search(LearningData learningDataFilter) {
		Example<LearningData> example = Example.of(learningDataFilter,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return learningDataRepository.findAll(example);
	}

	public LearningData getLearningDatasourceByPersonalDataType(Long idPersonalDataType) {
		return learningDataRepository.findLearningDatasourceByPersonalDataType(idPersonalDataType);
	}
}
