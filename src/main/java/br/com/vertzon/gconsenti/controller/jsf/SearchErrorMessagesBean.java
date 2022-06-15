package br.com.vertzon.gconsenti.controller.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.model.ErrorMessage;
import br.com.vertzon.gconsenti.domain.model.JsfLabel;
import br.com.vertzon.gconsenti.domain.model.enumerator.JSFBeanEnum;
import br.com.vertzon.gconsenti.domain.repository.ErrorMessageRepository;
import br.com.vertzon.gconsenti.domain.repository.JsfLabelRepository;
import br.com.vertzon.gconsenti.domain.service.ErrorMessageService;

@Component
@Scope("view")
public class SearchErrorMessagesBean {

	@Autowired
	private ErrorMessageRepository errorMessagesRepository;
	@Autowired
	private ErrorMessageService errorMessageService;
	@Autowired
	private JsfLabelRepository jsfLabelRepository;
	
	private List<ErrorMessage> errorMessages;
	private ErrorMessage errorMessage;
	
	private List<JsfLabel> errorMessageObjects = new ArrayList<>();
	private List<JsfLabel> genericObjects = new ArrayList<>();
	private List<String> errorMessageLabels = new ArrayList<>();
	private List<String> genericLabels = new ArrayList<>();
	
	
	@PostConstruct
	public void init() {
		errorMessageObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.ERROR_MESSAGE_SRC);
		genericObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.GENERIC_SRC);
		
		for (JsfLabel errorMessageLabel : errorMessageObjects) {
			errorMessageLabels.add(errorMessageLabel.getLabel());
		}
		
		for (JsfLabel genericLabel : genericObjects) {
			genericLabels.add(genericLabel.getLabel());
		}
		
		this.clear();
	}
	
	public void clear() {
		errorMessages = errorMessagesRepository.findAll();
		errorMessage = new ErrorMessage();
	}
	
	public void search() {
		if (errorMessage != null) {
			errorMessages = errorMessageService.search(errorMessage);
		} else {
			clear();
		}
	}

	public List<ErrorMessage> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List<ErrorMessage> errorMessages) {
		this.errorMessages = errorMessages;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<String> getErrorMessageLabels() {
		return errorMessageLabels;
	}

	public void setErrorMessageLabels(List<String> errorMessageLabels) {
		this.errorMessageLabels = errorMessageLabels;
	}

	public List<String> getGenericLabels() {
		return genericLabels;
	}

	public void setGenericLabels(List<String> genericLabels) {
		this.genericLabels = genericLabels;
	}
	
}

