package br.com.vertzon.gconsenti.controller.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.model.JsfLabel;
import br.com.vertzon.gconsenti.domain.model.enumerator.JSFBeanEnum;
import br.com.vertzon.gconsenti.domain.repository.JsfLabelRepository;
import br.com.vertzon.gconsenti.domain.service.JsfLabelService;

@Component
@Scope("view")
public class SearchJsfLabelBean {
	
	@Autowired
	private JsfLabelRepository jsfLabelRepository;
	@Autowired
	private JsfLabelService jsfLabelService;
	
	private List<JsfLabel> jsfLabels;
	private JsfLabel jsfLabel;
	
	private List<JsfLabel> jsfLabelObjects = new ArrayList<>();
	private List<JsfLabel> genericObjects = new ArrayList<>();
	private List<String> jsfLabelNames = new ArrayList<>();
	private List<String> genericLabels = new ArrayList<>();
	
	
	@PostConstruct
	public void init() {
		jsfLabelObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.JSF_LABEL_SRC);
		genericObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.GENERIC_SRC);
		
		for (JsfLabel jsfLabelName : jsfLabelObjects) {
			jsfLabelNames.add(jsfLabelName.getLabel());
		}
		
		for (JsfLabel genericLabel : genericObjects) {
			genericLabels.add(genericLabel.getLabel());
		}
		
		this.clear();
	}

	public void search() {
		if (jsfLabel != null) {
			jsfLabels = jsfLabelService.search(jsfLabel);
		} else {
			clear();
		}
	}
	
	public void clear() {
		jsfLabels = jsfLabelRepository.findAll();
		jsfLabel = new JsfLabel();
	}


	public List<JsfLabel> getJsfLabels() {
		return jsfLabels;
	}

	public void setJsfLabels(List<JsfLabel> jsfLabels) {
		this.jsfLabels = jsfLabels;
	}

	public JsfLabel getJsfLabel() {
		return jsfLabel;
	}

	public void setJsfLabel(JsfLabel jsfLabel) {
		this.jsfLabel = jsfLabel;
	}

	public List<String> getJsfLabelNames() {
		return jsfLabelNames;
	}

	public void setJsfLabelNames(List<String> jsfLabelNames) {
		this.jsfLabelNames = jsfLabelNames;
	}

	public List<String> getGenericLabels() {
		return genericLabels;
	}

	public void setGenericLabels(List<String> genericLabels) {
		this.genericLabels = genericLabels;
	}
	
}
