package br.com.vertzon.gconsenti.controller.jsf;

import java.io.IOException;
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
import br.com.vertzon.gconsenti.domain.repository.JsfLabelRepository;
import br.com.vertzon.gconsenti.domain.service.AppConfigurationService;
import br.com.vertzon.gconsenti.util.jsf.FacesUtil;

@Component
@Scope("view")
public class RegisterAppConfigurationBean implements Serializable {
	private static final long serialVersionUID = 7274054190849026027L;
	
	@Autowired
	private AppConfigurationService appConfigurationService;
	@Autowired
	private JsfLabelRepository jsfLabelRepository;
	@Autowired
	private RouterBean router;
	
	private AppConfiguration appConfiguration;
	
	private List<String> labels = new ArrayList<>();
	private List<JsfLabel> jsfLabels = new ArrayList<>();
	

	@PostConstruct
	public void init() {
		jsfLabels = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.APP_CONFIGURATION_REG);
		
		for (JsfLabel jsfLabel : jsfLabels) {
			labels.add(jsfLabel.getLabel());
		}
		
		this.clear();
	}
	
	public void clear() {
		this.appConfiguration = new AppConfiguration();
		
		
	}
	
	public void register() throws BusinessRuleException, IOException {		
		try {
			this.appConfigurationService.register(appConfiguration);
			FacesUtil.submitSuccess("A configuração foi cadastrada com sucesso!", router.getSearchAppConfigurationsPage());
		} catch (BusinessRuleException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public AppConfiguration getAppConfiguration() {
		return appConfiguration;
	}
	public void setAppConfiguration(AppConfiguration appConfiguration) {
		this.appConfiguration = appConfiguration;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	
}