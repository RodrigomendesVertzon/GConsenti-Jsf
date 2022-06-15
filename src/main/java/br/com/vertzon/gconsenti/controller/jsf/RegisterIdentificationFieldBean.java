package br.com.vertzon.gconsenti.controller.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.exception.BusinessRuleException;
import br.com.vertzon.gconsenti.domain.model.IdentificationColumn;
import br.com.vertzon.gconsenti.domain.model.IdentificationField;
import br.com.vertzon.gconsenti.domain.model.JsfLabel;
import br.com.vertzon.gconsenti.domain.model.PersonalDataType;
import br.com.vertzon.gconsenti.domain.model.enumerator.JSFBeanEnum;
import br.com.vertzon.gconsenti.domain.repository.IdentificationColumnRepository;
import br.com.vertzon.gconsenti.domain.repository.JsfLabelRepository;
import br.com.vertzon.gconsenti.domain.repository.PersonalDataTypeRepository;
import br.com.vertzon.gconsenti.domain.service.IdentificationFieldService;
import br.com.vertzon.gconsenti.util.jsf.FacesUtil;

@Component
@Scope("view")
public class RegisterIdentificationFieldBean implements Serializable {
	private static final long serialVersionUID = 7274054190849026027L;

	@Autowired
	private IdentificationFieldService identificationFieldService;
	@Autowired
	private PersonalDataTypeRepository personalDataTypeRepository;
	@Autowired
	private IdentificationColumnRepository identificationColumnRepository;
	@Autowired
	private RouterBean router;
	@Autowired
	private JsfLabelRepository jsfLabelRepository;
	

	private IdentificationField identificationField;
	private List<PersonalDataType> personalDataTypes;
	private List<IdentificationColumn> identificationColumns;
	private List<IdentificationColumn> identificationColumnFields;
	private List<String> labels = new ArrayList<String>();
	private List<JsfLabel> jsfLabels = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		this.identificationField = new IdentificationField();
		this.personalDataTypes = this.personalDataTypeRepository.findAll();
		this.identificationColumns = this.identificationColumnRepository.findAll();
		jsfLabels = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.IDENTIFICATION_FIELD_REG);
		
		for (JsfLabel jsfLabel : jsfLabels) {
			labels.add(jsfLabel.getLabel());
		}
	}

	public void register() throws BusinessRuleException {
		try {
			this.identificationFieldService.register(identificationField);
			FacesUtil.submitSuccess("O campo de identificação foi cadastrado com sucesso!", router.getSearchIdentificationFieldsPage());
		} catch (BusinessRuleException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public IdentificationField getIdentificationField() {
		return identificationField;
	}

	public void setIdentificationField(IdentificationField identificationField) {
		this.identificationField = identificationField;
	}

	public List<PersonalDataType> getPersonalDataTypes() {
		return personalDataTypes;
	}

	public void setPersonalDataTypes(List<PersonalDataType> personalDataTypes) {
		this.personalDataTypes = personalDataTypes;
	}

	public List<IdentificationColumn> getIdentificationColumns() {
		return identificationColumns;
	}

	public void setIdentificationColumns(List<IdentificationColumn> identificationColumns) {
		this.identificationColumns = identificationColumns;
	}

	public List<IdentificationColumn> getIdentificationColumnFields() {
		return identificationColumnFields;
	}

	public void setIdentificationColumnFields(List<IdentificationColumn> identificationColumnFields) {
		this.identificationColumnFields = identificationColumnFields;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	
	
}
