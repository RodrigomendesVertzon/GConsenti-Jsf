package br.com.vertzon.gconsenti.controller.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.exception.BusinessRuleException;
import br.com.vertzon.gconsenti.domain.model.IdentificationField;
import br.com.vertzon.gconsenti.domain.model.JsfLabel;
import br.com.vertzon.gconsenti.domain.model.enumerator.JSFBeanEnum;
import br.com.vertzon.gconsenti.domain.repository.IdentificationFieldRepository;
import br.com.vertzon.gconsenti.domain.repository.JsfLabelRepository;
import br.com.vertzon.gconsenti.domain.service.IdentificationFieldService;
import br.com.vertzon.gconsenti.util.jsf.FacesUtil;

@Component
@Scope("view")
public class SearchIdentificationFieldBean implements Serializable {
	private static final long serialVersionUID = -4603180026903804948L;
	
	@Autowired
	private IdentificationFieldRepository identificationFieldRepository;
	@Autowired
	private IdentificationFieldService identificationFieldService;
	@Autowired
	private JsfLabelRepository jsfLabelRepository;
	
	private List<IdentificationField> identificationFields;
	private IdentificationField identificationField;	
	
	private List<JsfLabel> identificationFieldObjects = new ArrayList<>();
	private List<JsfLabel> genericObjects = new ArrayList<>();
	private List<String> identificationFieldLabels = new ArrayList<>();
	private List<String> genericLabels = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		identificationFieldObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.IDENTIFICATION_FIELD_SRC);
		genericObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.GENERIC_SRC);
		
		for (JsfLabel identificationFieldLabel : identificationFieldObjects) {
			identificationFieldLabels.add(identificationFieldLabel.getLabel());
		}
		
		for (JsfLabel genericLabel : genericObjects) {
			genericLabels.add(genericLabel.getLabel());
		}
		
		this.clear();
	}
	
	public void search() {
		if (this.identificationField != null) {
			this.identificationFields = this.identificationFieldService.search(identificationField);
		} else {
			this.clear();
		}
	}
	
	public void delete() {
		try {
			identificationFieldService.delete(identificationField.getId());
			identificationFields.remove(identificationField);
		} catch (BusinessRuleException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void clear() {
		this.identificationFields = this.identificationFieldRepository.findAll();
		this.identificationField = new IdentificationField();
	}
	
	
	
	public List<IdentificationField> getIdentificationFields() {
		return identificationFields;
	}
	public void setIdentificationFields(List<IdentificationField> identificationFields) {
		this.identificationFields = identificationFields;
	}

	public IdentificationField getIdentificationField() {
		return identificationField;
	}
	public void setIdentificationField(IdentificationField identificationField) {
		this.identificationField = identificationField;
	}

	public List<String> getIdentificationFieldLabels() {
		return identificationFieldLabels;
	}

	public void setIdentificationFieldLabels(List<String> identificationFieldLabels) {
		this.identificationFieldLabels = identificationFieldLabels;
	}

	public List<String> getGenericLabels() {
		return genericLabels;
	}

	public void setGenericLabels(List<String> genericLabels) {
		this.genericLabels = genericLabels;
	}
	
}
