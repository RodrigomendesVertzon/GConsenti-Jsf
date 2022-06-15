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
import br.com.vertzon.gconsenti.domain.model.PersonalDataType;
import br.com.vertzon.gconsenti.domain.model.enumerator.JSFBeanEnum;
import br.com.vertzon.gconsenti.domain.repository.JsfLabelRepository;
import br.com.vertzon.gconsenti.domain.repository.PersonalDataTypeRepository;
import br.com.vertzon.gconsenti.domain.service.PersonalDataTypeService;

@Component
@Scope("view")
public class SearchPersonalDataTypeBean implements Serializable {
	private static final long serialVersionUID = -4603180026903804948L;
	
	@Autowired
	private PersonalDataTypeRepository personalDataTypeRepository;
	@Autowired
	private PersonalDataTypeService personalDataTypeService;
	@Autowired
	private JsfLabelRepository jsfLabelRepository;
	
	private List<PersonalDataType> personalDataTypes;
	private PersonalDataType personalDataType;
	
	private List<JsfLabel> personalDataTypeObjects = new ArrayList<>();
	private List<JsfLabel> genericObjects = new ArrayList<>();
	private List<String> personalDataTypeLabels = new ArrayList<>();
	private List<String> genericLabels = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		personalDataTypeObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.PERSONAL_DATA_TYPE_SRC);
		genericObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.GENERIC_SRC);
		
		for (JsfLabel personalDataTypeLabel : personalDataTypeObjects) {
			personalDataTypeLabels.add(personalDataTypeLabel.getLabel());
		}
		
		for (JsfLabel genericLabel : genericObjects) {
			genericLabels.add(genericLabel.getLabel());
		}
		
		this.clear();
	}
	
	public void search() {
		if (this.personalDataType != null) {
			this.personalDataTypes = this.personalDataTypeService.search(personalDataType);
		} else {
			this.clear();
		}
	}
	
	public void delete() {
		try {
			personalDataTypeService.delete(personalDataType.getId());
			personalDataTypes.remove(personalDataType);
		} catch (BusinessRuleException e) {
			e.getMessage();
		}
	}
	
	public void clear() {
		this.personalDataTypes = this.personalDataTypeRepository.findAll();
		this.personalDataType = new PersonalDataType();
	}
	
	
	
	public List<PersonalDataType> getPersonalDataTypes() {
		return personalDataTypes;
	}
	public void setPersonalDataTypes(List<PersonalDataType> personalDataTypes) {
		this.personalDataTypes = personalDataTypes;
	}

	public PersonalDataType getPersonalDataType() {
		return personalDataType;
	}
	public void setPersonalDataType(PersonalDataType personalDataType) {
		this.personalDataType = personalDataType;
	}

	public List<String> getPersonalDataTypeLabels() {
		return personalDataTypeLabels;
	}

	public void setPersonalDataTypeLabels(List<String> personalDataTypeLabels) {
		this.personalDataTypeLabels = personalDataTypeLabels;
	}

	public List<String> getGenericLabels() {
		return genericLabels;
	}

	public void setGenericLabels(List<String> genericLabels) {
		this.genericLabels = genericLabels;
	}
	
}
