package br.com.vertzon.gconsenti.controller.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.exception.BusinessRuleException;
import br.com.vertzon.gconsenti.domain.model.Datasource;
import br.com.vertzon.gconsenti.domain.model.JsfLabel;
import br.com.vertzon.gconsenti.domain.model.LearningData;
import br.com.vertzon.gconsenti.domain.model.PersonalDataType;
import br.com.vertzon.gconsenti.domain.model.enumerator.JSFBeanEnum;
import br.com.vertzon.gconsenti.domain.repository.DatasourceRepository;
import br.com.vertzon.gconsenti.domain.repository.JsfLabelRepository;
import br.com.vertzon.gconsenti.domain.repository.PersonalDataTypeRepository;
import br.com.vertzon.gconsenti.domain.service.LearningDataService;
import br.com.vertzon.gconsenti.util.jsf.FacesUtil;

@Component
@Scope("view")
public class RegisterLearningDataBean implements Serializable {
	private static final long serialVersionUID = 7274054190849026027L;
	
	@Autowired
	private LearningDataService learningDataService;
	@Autowired
	private DatasourceRepository datasourceRepository;
	@Autowired
	private PersonalDataTypeRepository personalDataTypeRepository;
	@Autowired
	private RouterBean router;
	@Autowired
	private JsfLabelRepository jsfLabelRepository;
	
	private LearningData learningData;
	private List<Datasource> datasources;
	private List<PersonalDataType> personalDataTypes;
	private List<String> labels = new ArrayList<String>(); 
	private List<JsfLabel> jsfLabels = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		this.learningData = new LearningData();
		this.datasources = this.datasourceRepository.findAll();
		this.personalDataTypes = this.personalDataTypeRepository.findAll();
		jsfLabels = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.LEARNING_DATA_REG);
		
		for (JsfLabel jsfLabel : jsfLabels) {
			labels.add(jsfLabel.getLabel());
		}
	}
	
	public void register() throws BusinessRuleException {		
		try {
			this.learningDataService.register(learningData);
			FacesUtil.submitSuccess("A fonte de aprendizado foi cadastrada com sucesso!", router.getSearchLearningDatasPage());
		} catch (BusinessRuleException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public LearningData getLearningData() {
		return learningData;
	}
	public void setLearningData(LearningData learningData) {
		this.learningData = learningData;
	}

	public List<Datasource> getDatasources() {
		return datasources;
	}
	public void setDatasources(List<Datasource> datasources) {
		this.datasources = datasources;
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
