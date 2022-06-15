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
import br.com.vertzon.gconsenti.domain.repository.PersonalDataTypeRepository;
import br.com.vertzon.gconsenti.domain.service.LearningScriptService;
import br.com.vertzon.gconsenti.util.jsf.FacesUtil;

@Component
@Scope("view")
public class RegisterLearningScriptBean implements Serializable {
	private static final long serialVersionUID = 7274054190849026027L;
	
	@Autowired
	private LearningScriptService learningScriptService;
	@Autowired
	private PersonalDataTypeRepository personalDataTypeRepository;
	@Autowired
	private RouterBean router;
	@Autowired
	private JsfLabelRepository jsfLabelRepository;
	
	private LearningScript learningScript;
	private List<PersonalDataType> personalDataTypes;
	private List<String> labels = new ArrayList<String>();
	private List<JsfLabel> jsfLabels = new ArrayList<>();
	
	
	@PostConstruct
	public void init() {
		this.learningScript = new LearningScript();
		this.personalDataTypes = this.personalDataTypeRepository.findAll();
		jsfLabels = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.LEARNING_SCRIPT_REG);
		
		for (JsfLabel jsfLabel : jsfLabels) {
			labels.add(jsfLabel.getLabel());
		}
	}
	
	public void register() throws BusinessRuleException {		
		try {
			this.learningScriptService.register(learningScript);
			FacesUtil.submitSuccess("O script foi cadastrado com sucesso!", router.getSearchLearningScriptsPage());
		} catch (BusinessRuleException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	
	
	public LearningScript getLearningScript() {
		return learningScript;
	}
	public void setlearningScript(LearningScript learningScript) {
		this.learningScript = learningScript;
	}

	public List<PersonalDataType> getPersonalDataTypes() {
		return personalDataTypes;
	}
	public void setPersonalDataTypes(List<PersonalDataType> personalDataTypes) {
		this.personalDataTypes = personalDataTypes;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	
}
