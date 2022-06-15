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
import br.com.vertzon.gconsenti.domain.model.LearningScript;
import br.com.vertzon.gconsenti.domain.model.PersonalDataType;
import br.com.vertzon.gconsenti.domain.model.enumerator.JSFBeanEnum;
import br.com.vertzon.gconsenti.domain.repository.JsfLabelRepository;
import br.com.vertzon.gconsenti.domain.repository.LearningScriptRepository;
import br.com.vertzon.gconsenti.domain.repository.PersonalDataTypeRepository;
import br.com.vertzon.gconsenti.domain.service.LearningScriptService;

@Component
@Scope("view")
public class SearchLearningScriptBean implements Serializable {
	private static final long serialVersionUID = -4603180026903804948L;
	
	@Autowired
	private LearningScriptRepository learningScriptRepository;
	@Autowired
	private LearningScriptService learningScriptService;
	@Autowired
	private PersonalDataTypeRepository personalDataTypeRepository;
	@Autowired
	private JsfLabelRepository jsfLabelRepository;
	
	private List<LearningScript> learningScripts;
	private List<PersonalDataType> personalDataTypes;
	private LearningScript learningScript;
	
	private List<JsfLabel> learningScriptObjects = new ArrayList<>();
	private List<JsfLabel> genericObjects = new ArrayList<>();
	private List<String> learningScriptLabels = new ArrayList<>();
	private List<String> genericLabels = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		learningScriptObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.LEARNING_SCRIPT_SRC);
		genericObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.GENERIC_SRC);
		
		for (JsfLabel learningScriptLabel : learningScriptObjects) {
			learningScriptLabels.add(learningScriptLabel.getLabel());
		}
		
		for (JsfLabel genericLabel : genericObjects) {
			genericLabels.add(genericLabel.getLabel());
		}
		
		this.clear();
	}
	
	public void search() {
		if (this.learningScript != null) {
			this.learningScripts = this.learningScriptService.search(learningScript);
		} else {
			this.clear();
		}
		
		this.learningScript = new LearningScript();
	}
	
	public void clear() {
		this.learningScripts = this.learningScriptRepository.findAll();
		this.learningScript = new LearningScript();
		this.personalDataTypes = this.personalDataTypeRepository.findAll();
	}
	
	public void delete() {
		try {
			learningScriptService.delete(learningScript.getId());
			learningScripts.remove(learningScript);
		} catch (BusinessRuleException e) {
			e.getMessage();
		}
	}
	
	public List<LearningScript> getLearningScripts() {
		return learningScripts;
	}
	public void setLearningScripts(List<LearningScript> learningScripts) {
		this.learningScripts = learningScripts;
	}

	public LearningScript getLearningScript() {
		return learningScript;
	}
	public void setLearningScript(LearningScript learningScript) {
		this.learningScript = learningScript;
	}

	public List<PersonalDataType> getPersonalDataTypes() {
		return personalDataTypes;
	}
	public void setPersonalDataTypes(List<PersonalDataType> personalDataTypes) {
		this.personalDataTypes = personalDataTypes;
	}

	public List<String> getLearningScriptLabels() {
		return learningScriptLabels;
	}

	public void setLearningScriptLabels(List<String> learningScriptLabels) {
		this.learningScriptLabels = learningScriptLabels;
	}

	public List<String> getGenericLabels() {
		return genericLabels;
	}

	public void setGenericLabels(List<String> genericLabels) {
		this.genericLabels = genericLabels;
	}
	
}
