package br.com.vertzon.gconsenti.controller.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.exception.BusinessRuleException;
import br.com.vertzon.gconsenti.domain.model.Datasource;
import br.com.vertzon.gconsenti.domain.model.JsfLabel;
import br.com.vertzon.gconsenti.domain.model.enumerator.JSFBeanEnum;
import br.com.vertzon.gconsenti.domain.repository.DatasourceRepository;
import br.com.vertzon.gconsenti.domain.repository.JsfLabelRepository;
import br.com.vertzon.gconsenti.domain.service.DatasourceService;
import br.com.vertzon.gconsenti.util.jsf.FacesUtil;

@Component
@ViewScoped
public class SearchDatasourceBean implements Serializable {
	
	private static final long serialVersionUID = -4603180026903804948L;
	
	@Autowired
	private DatasourceRepository datasourceRepository;
	@Autowired
	private DatasourceService datasourceService;
	@Autowired
	private JsfLabelRepository jsfLabelRepository;
	
	private List<Datasource> datasources;
	private Datasource datasource;	
	
	private List<JsfLabel> datasourceObjects = new ArrayList<>();
	private List<JsfLabel> genericObjects = new ArrayList<>();
	private List<String> datasourceLabels = new ArrayList<>();
	private List<String> genericLabels = new ArrayList<>();
	
	
	@PostConstruct
	public void init() {
		datasourceObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.DATASOURCE_SRC);
		genericObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.GENERIC_SRC);
		
		for (JsfLabel datasourceLabel : datasourceObjects) {
			datasourceLabels.add(datasourceLabel.getLabel());
		}
		
		for (JsfLabel genericLabel : genericObjects) {
			genericLabels.add(genericLabel.getLabel());
		}
		
		this.clear();
	}
	
	public void search() {
		if (this.datasource != null) {
			this.datasources = this.datasourceService.search(datasource);
		} else {
			this.clear();
		}
	}
	
	public void delete() {
		try {
			datasourceService.delete(datasource.getId());
			datasources.remove(datasource);
		} catch (BusinessRuleException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		
	}
	
	public void clear() {
		this.datasources = this.datasourceRepository.findAll();
		this.datasource = new Datasource();
	}
	
	
	public List<Datasource> getDatasources() {
		return datasources;
	}
	public void setDatasources(List<Datasource> Datasources) {
		this.datasources = Datasources;
	}

	public Datasource getDatasource() {
		return datasource;
	}
	public void setDatasource(Datasource Datasource) {
		this.datasource = Datasource;
	}

	public List<String> getDatasourceLabels() {
		return datasourceLabels;
	}

	public void setDatasourceLabels(List<String> datasourceLabels) {
		this.datasourceLabels = datasourceLabels;
	}

	public List<String> getGenericLabels() {
		return genericLabels;
	}

	public void setGenericLabels(List<String> genericLabels) {
		this.genericLabels = genericLabels;
	}
	
}
