package br.com.vertzon.gconsenti.controller.jsf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.exception.BusinessRuleException;
import br.com.vertzon.gconsenti.domain.model.JsfLabel;
import br.com.vertzon.gconsenti.domain.model.enumerator.JSFBeanEnum;
import br.com.vertzon.gconsenti.domain.repository.JsfLabelRepository;
import br.com.vertzon.gconsenti.domain.service.JsfLabelService;
import br.com.vertzon.gconsenti.util.jsf.FacesUtil;

@Component
@Scope("view")
public class RegisterJsfLabelBean {
	
	@Autowired
	private JsfLabelRepository jsfLabelRepository;
	@Autowired
	private JsfLabelService jsfLabelService;
	@Autowired
	private RouterBean router;
	
	private JsfLabel jsfLabel;
	
	private List<String> labels = new ArrayList<>();
	private List<JsfLabel> jsfLabels = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		jsfLabels = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.JSF_LABEL_REG);
		
		for (JsfLabel jsfLabel : jsfLabels) {
			labels.add(jsfLabel.getLabel());
		}
		this.clear();
	}
	
	public void clear() {
		this.jsfLabel = new JsfLabel();
	}
	
	public void register() throws BusinessRuleException, IOException {		
		try {
			this.jsfLabelService.register(jsfLabel);
			FacesUtil.submitSuccess("A configuração foi cadastrada com sucesso!", router.getSearchJsfLabelsPage());
		} catch (BusinessRuleException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public JsfLabel getJsfLabel() {
		return jsfLabel;
	}

	public void setJsfLabel(JsfLabel jsfLabel) {
		this.jsfLabel = jsfLabel;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	
}
