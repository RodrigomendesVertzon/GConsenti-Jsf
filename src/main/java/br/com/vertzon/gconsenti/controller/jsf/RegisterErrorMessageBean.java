package br.com.vertzon.gconsenti.controller.jsf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.exception.BusinessRuleException;
import br.com.vertzon.gconsenti.domain.model.ErrorMessage;
import br.com.vertzon.gconsenti.domain.model.JsfLabel;
import br.com.vertzon.gconsenti.domain.model.enumerator.JSFBeanEnum;
import br.com.vertzon.gconsenti.domain.repository.JsfLabelRepository;
import br.com.vertzon.gconsenti.domain.service.ErrorMessageService;
import br.com.vertzon.gconsenti.util.jsf.FacesUtil;

@Component
@Scope("view")
public class RegisterErrorMessageBean {

	@Autowired
	private RouterBean router;
	@Autowired
	private ErrorMessageService errorMessageService;
	@Autowired
	private JsfLabelRepository jsfLabelRepository;
	
	private ErrorMessage errorMessage;
	private List<String> labels = new ArrayList<>();
	private List<JsfLabel> jsfLabels = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		jsfLabels = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.ERROR_MESSAGE_REG);
		
		for (JsfLabel jsfLabel : jsfLabels) {
			labels.add(jsfLabel.getLabel());
		}
		this.clear();
	}
	
	public void clear() {
		this.errorMessage = new ErrorMessage();
	}
	
	public void register() throws BusinessRuleException, IOException {		
		try {
			this.errorMessageService.register(errorMessage);
			FacesUtil.submitSuccess("A configuração foi cadastrada com sucesso!", router.getSearchErrorMessagesPage());
		} catch (BusinessRuleException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	

}
