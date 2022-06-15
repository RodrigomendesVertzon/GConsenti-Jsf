package br.com.vertzon.gconsenti.controller.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.exception.BusinessRuleException;
import br.com.vertzon.gconsenti.domain.model.DatabaseType;
import br.com.vertzon.gconsenti.domain.model.JsfLabel;
import br.com.vertzon.gconsenti.domain.model.enumerator.JSFBeanEnum;
import br.com.vertzon.gconsenti.domain.repository.DatabaseTypeRepository;
import br.com.vertzon.gconsenti.domain.repository.JsfLabelRepository;
import br.com.vertzon.gconsenti.domain.service.DatabaseTypeService;
import br.com.vertzon.gconsenti.util.jsf.FacesUtil;

@Component
@Scope("view")
public class SearchDatabaseTypeBean implements Serializable {
	private static final long serialVersionUID = -4603180026903804948L;
	
	@Autowired
	private DatabaseTypeRepository databaseTypeRepository;
	@Autowired
	private DatabaseTypeService databaseTypeService;
	@Autowired
	private JsfLabelRepository jsfLabelRepository;
	
	private List<DatabaseType> databaseTypes;
	private DatabaseType databaseType = new DatabaseType();
	
	private List<JsfLabel> databaseTypeObjects = new ArrayList<>();
	private List<JsfLabel> genericObjects = new ArrayList<>();
	private List<String> databaseTypeLabels = new ArrayList<>();
	private List<String> genericLabels = new ArrayList<>();
	
	
	@PostConstruct
	public void init() {
		databaseTypeObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.DATABASE_TYPE_SRC);
		genericObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.GENERIC_SRC);
		
		for (JsfLabel databaseTypeLabel : databaseTypeObjects) {
			databaseTypeLabels.add(databaseTypeLabel.getLabel());
		}
		
		for (JsfLabel genericLabel : genericObjects) {
			genericLabels.add(genericLabel.getLabel());
		}
		
		this.clear();
	}
	
	public void search() {
		if (this.databaseType != null) {
			this.databaseTypes = this.databaseTypeService.search(databaseType);
		} else {
			this.clear();
		}
	}
	
	public void clear() {
		this.databaseTypes = this.databaseTypeRepository.findAll();
		this.databaseType = new DatabaseType();
	}
	
	public void delete() {
		try {
			databaseTypeService.delete(databaseType.getId());
			databaseTypes.remove(databaseType);
		} catch (BusinessRuleException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	
	public List<DatabaseType> getDatabaseTypes() {
		return databaseTypes;
	}
	public void setDatabaseTypes(List<DatabaseType> databaseTypes) {
		this.databaseTypes = databaseTypes;
	}

	public DatabaseType getDatabaseType() {
		return databaseType;
	}
	public void setDatabaseType(DatabaseType databaseType) {
		this.databaseType = databaseType;
	}

	public List<String> getDatabaseTypeLabels() {
		return databaseTypeLabels;
	}

	public void setDatabaseTypeLabels(List<String> databaseTypeLabels) {
		this.databaseTypeLabels = databaseTypeLabels;
	}

	public List<String> getGenericLabels() {
		return genericLabels;
	}

	public void setGenericLabels(List<String> genericLabels) {
		this.genericLabels = genericLabels;
	}
	
}
