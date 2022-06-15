package br.com.vertzon.gconsenti.controller.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.exception.BusinessRuleException;
import br.com.vertzon.gconsenti.domain.model.AppConfiguration;
import br.com.vertzon.gconsenti.domain.model.JsfLabel;
import br.com.vertzon.gconsenti.domain.model.enumerator.JSFBeanEnum;
import br.com.vertzon.gconsenti.domain.repository.AppConfigurationRepository;
import br.com.vertzon.gconsenti.domain.repository.JsfLabelRepository;
import br.com.vertzon.gconsenti.domain.service.AppConfigurationService;
import br.com.vertzon.gconsenti.util.jsf.FacesUtil;

@Component
@Scope("view")
public class SearchAppConfigurationBean implements Serializable {
	private static final long serialVersionUID = -4603180026903804948L;
	
	@Autowired
	private AppConfigurationRepository appConfigurationRepository;
	@Autowired
	private AppConfigurationService appConfigurationService;
	@Autowired
	private JsfLabelRepository jsfLabelRepository;
	
	private List<AppConfiguration> appConfigurations;
	private AppConfiguration appConfiguration;
	
	private List<JsfLabel> appConfigurationObjects = new ArrayList<>();
	private List<JsfLabel> genericObjects = new ArrayList<>();
	private List<String> appConfigurationLabels = new ArrayList<>();
	private List<String> genericLabels = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		appConfigurationObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.APP_CONFIGURATION_SRC);
		genericObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.GENERIC_SRC);
		
		for (JsfLabel appConfigurationLabel : appConfigurationObjects) {
			appConfigurationLabels.add(appConfigurationLabel.getLabel());
		}
		
		for (JsfLabel genericLabel : genericObjects) {
			genericLabels.add(genericLabel.getLabel());
		}
		
		this.clear();
	}
	
	public void clear() {
		appConfigurations = appConfigurationRepository.findAll();
		appConfiguration = new AppConfiguration();
	}
	
	public void search() {
		if (appConfiguration != null) {
			appConfigurations = appConfigurationService.search(appConfiguration);
		} else {
			clear();
		}
	}
	
	public void delete() {
		try {
			appConfigurationService.delete(appConfiguration.getId());
			appConfigurations.remove(appConfiguration);
		} catch (BusinessRuleException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public List<AppConfiguration> getAppConfigurations() {
		return appConfigurations;
	}

	public void setAppConfigurations(List<AppConfiguration> appConfigurations) {
		this.appConfigurations = appConfigurations;
	}

	public AppConfiguration getAppConfiguration() {
		return appConfiguration;
	}

	public void setAppConfiguration(AppConfiguration appConfiguration) {
		this.appConfiguration = appConfiguration;
	}

	public List<String> getAppConfigurationLabels() {
		return appConfigurationLabels;
	}

	public void setAppConfigurationLabels(List<String> appConfigurationLabels) {
		this.appConfigurationLabels = appConfigurationLabels;
	}

	public List<String> getGenericLabels() {
		return genericLabels;
	}

	public void setGenericLabels(List<String> genericLabels) {
		this.genericLabels = genericLabels;
	}

}
