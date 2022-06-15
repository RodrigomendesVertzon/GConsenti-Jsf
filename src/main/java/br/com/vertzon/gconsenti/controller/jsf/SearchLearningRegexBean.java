package br.com.vertzon.gconsenti.controller.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.exception.BusinessRuleException;
import br.com.vertzon.gconsenti.domain.model.JsfLabel;
import br.com.vertzon.gconsenti.domain.model.LearningRegex;
import br.com.vertzon.gconsenti.domain.model.PersonalDataType;
import br.com.vertzon.gconsenti.domain.model.enumerator.JSFBeanEnum;
import br.com.vertzon.gconsenti.domain.repository.JsfLabelRepository;
import br.com.vertzon.gconsenti.domain.repository.LearningRegexRepository;
import br.com.vertzon.gconsenti.domain.repository.PersonalDataTypeRepository;
import br.com.vertzon.gconsenti.domain.service.LearningRegexService;

@Component
@Scope("view")
public class SearchLearningRegexBean implements Serializable {
	private static final long serialVersionUID = -4603180026903804948L;
	
	@Autowired
	private LearningRegexRepository learningRegexRepository;
	@Autowired
	private LearningRegexService learningRegexService;
	@Autowired
	private PersonalDataTypeRepository personalDataTypeRepository;
	@Autowired
	private JsfLabelRepository jsfLabelRepository;
	
	private List<LearningRegex> learningRegexes;
	private List<PersonalDataType> personalDataTypes;
	private LearningRegex learningRegex;
	
	private List<JsfLabel> learningRegexObjects = new ArrayList<>();
	private List<JsfLabel> genericObjects = new ArrayList<>();
	private List<String> learningRegexLabels = new ArrayList<>();
	private List<String> genericLabels = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		learningRegexObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.LEARNING_REGEX_SRC);
		genericObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.GENERIC_SRC);
		
		for (JsfLabel learningRegexLabel : learningRegexObjects) {
			learningRegexLabels.add(learningRegexLabel.getLabel());
		}
		
		for (JsfLabel genericLabel : genericObjects) {
			genericLabels.add(genericLabel.getLabel());
		}
		
		this.clear();
	}
	
	public void search() {
		if (this.learningRegex != null) {
			this.learningRegexes = this.learningRegexService.search(learningRegex);
		} else {
			this.clear();
		}
		
		this.learningRegex = new LearningRegex();
	}
	
	public void delete() {
		try {
			learningRegexService.delete(learningRegex.getId());
			learningRegexes.remove(learningRegex);
		} catch (BusinessRuleException e) {
			e.getMessage();
		}
	}
	
	public void clear() {
		this.learningRegexes = this.learningRegexRepository.findAll();
		this.learningRegex = new LearningRegex();
		this.personalDataTypes = this.personalDataTypeRepository.findAll();
	}
	
	
	
	public List<LearningRegex> getLearningRegexes() {
		return learningRegexes;
	}
	public void setLearningRegexes(List<LearningRegex> learningRegexes) {
		this.learningRegexes = learningRegexes;
	}

	public LearningRegex getLearningRegex() {
		return learningRegex;
	}
	public void setLearningRegex(LearningRegex learningRegex) {
		this.learningRegex = learningRegex;
	}

	public List<PersonalDataType> getPersonalDataTypes() {
		return personalDataTypes;
	}
	public void setPersonalDataTypes(List<PersonalDataType> personalDataTypes) {
		this.personalDataTypes = personalDataTypes;
	}

	public List<String> getLearningRegexLabels() {
		return learningRegexLabels;
	}

	public void setLearningRegexLabels(List<String> learningRegexLabels) {
		this.learningRegexLabels = learningRegexLabels;
	}

	public List<String> getGenericLabels() {
		return genericLabels;
	}

	public void setGenericLabels(List<String> genericLabels) {
		this.genericLabels = genericLabels;
	}
	
}
