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
import br.com.vertzon.gconsenti.domain.model.enumerator.IdentificationMethodEnum;
import br.com.vertzon.gconsenti.domain.model.enumerator.JSFBeanEnum;
import br.com.vertzon.gconsenti.domain.model.enumerator.PersonalDataCategorizationEnum;
import br.com.vertzon.gconsenti.domain.repository.JsfLabelRepository;
import br.com.vertzon.gconsenti.domain.service.PersonalDataTypeService;
import br.com.vertzon.gconsenti.util.jsf.FacesUtil;

@Component
@Scope("view")
public class RegisterPersonalDataTypeBean implements Serializable {
	private static final long serialVersionUID = 7274054190849026027L;
	
	@Autowired
	private PersonalDataTypeService personalDataTypeService;
	@Autowired
	private RouterBean router;
	@Autowired
	private JsfLabelRepository jsfLabelRepository;
	
	private PersonalDataType personalDataType;
	private String methodValue;
	private String categoryValue;
	private List<String> labels = new ArrayList<String>();
	private List<JsfLabel> jsfLabels = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		this.personalDataType = new PersonalDataType();
		jsfLabels = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.PERSONAL_DATA_TYPE_REG);
		
		for (JsfLabel jsfLabel : jsfLabels) {
			labels.add(jsfLabel.getLabel());
		}
	}
	
	public void register() throws BusinessRuleException {		
		try {
			this.personalDataType.setMethod(IdentificationMethodEnum.valueOf(methodValue));
			this.personalDataType.setCategory(PersonalDataCategorizationEnum.valueOf(categoryValue));
			this.personalDataTypeService.register(personalDataType);
			FacesUtil.submitSuccess("O tipo de dado pessoal foi cadastrado com sucesso!", router.getSearchPersonalDataTypesPage());
		} catch (BusinessRuleException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}


	public PersonalDataType getPersonalDataType() {
		return personalDataType;
	}
	public void setpersonalDataType(PersonalDataType personalDataType) {
		this.personalDataType = personalDataType;
	}

	public String getMethodValue() {
		return methodValue;
	}
	public void setMethodValue(String methodValue) {
		this.methodValue = methodValue;
	}

	public String getCategoryValue() {
		return categoryValue;
	}
	public void setCategoryValue(String categoryValue) {
		this.categoryValue = categoryValue;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	
}
