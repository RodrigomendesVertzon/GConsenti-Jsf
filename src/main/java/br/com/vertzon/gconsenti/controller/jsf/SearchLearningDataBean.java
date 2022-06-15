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
import br.com.vertzon.gconsenti.domain.model.enumerator.JSFBeanEnum;
import br.com.vertzon.gconsenti.domain.repository.DatasourceRepository;
import br.com.vertzon.gconsenti.domain.repository.JsfLabelRepository;
import br.com.vertzon.gconsenti.domain.repository.LearningDataRepository;
import br.com.vertzon.gconsenti.domain.service.LearningDataService;

@Component
@Scope("view")
public class SearchLearningDataBean implements Serializable {
	private static final long serialVersionUID = -4603180026903804948L;
	
	@Autowired
	private LearningDataRepository learningDataRepository;
	@Autowired
	private LearningDataService learningDataService;
	@Autowired
	private DatasourceRepository datasourceRepository;
	@Autowired
	private JsfLabelRepository jsfLabelRepository;
	
	private List<LearningData> learningDatas;
	private List<Datasource> datasources;
	private LearningData learningData;
	
	private List<JsfLabel> learningDataObjects = new ArrayList<>();
	private List<JsfLabel> genericObjects = new ArrayList<>();
	private List<String> learningDataLabels = new ArrayList<>();
	private List<String> genericLabels = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		learningDataObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.LEARNING_DATA_SRC);
		genericObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.GENERIC_SRC);
		
		for (JsfLabel learningDataLabel : learningDataObjects) {
			learningDataLabels.add(learningDataLabel.getLabel());
		}
		
		for (JsfLabel genericLabel : genericObjects) {
			genericLabels.add(genericLabel.getLabel());
		}
		
		this.clear();
	}
	
	public void search() {
		if (this.learningData != null) {
			//this.LearningDatas = this.LearningDataService.search(LearningData);
		} else {
			this.clear();
		}
		
		this.learningData = new LearningData();
	}
	
	public void delete() {
		try {
			learningDataService.delete(learningData.getId());
			learningDatas.remove(learningData);
		} catch (BusinessRuleException e) {
			e.getMessage();
		}
	}
	
	public void clear() {
		this.learningDatas = this.learningDataRepository.findAll();
		this.learningData = new LearningData();
		this.datasources = this.datasourceRepository.findAll();
	}
	
	public List<LearningData> getLearningDatas() {
		return learningDatas;
	}
	public void setLearningDatas(List<LearningData> learningDatas) {
		this.learningDatas = learningDatas;
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
	public void setdatasources(List<Datasource> datasources) {
		this.datasources = datasources;
	}

	public List<String> getLearningDataLabels() {
		return learningDataLabels;
	}

	public void setLearningDataLabels(List<String> learningDataLabels) {
		this.learningDataLabels = learningDataLabels;
	}

	public List<String> getGenericLabels() {
		return genericLabels;
	}

	public void setGenericLabels(List<String> genericLabels) {
		this.genericLabels = genericLabels;
	}
	
}
