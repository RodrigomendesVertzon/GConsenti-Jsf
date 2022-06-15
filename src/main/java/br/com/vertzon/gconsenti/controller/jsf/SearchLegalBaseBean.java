package br.com.vertzon.gconsenti.controller.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.model.JsfLabel;
import br.com.vertzon.gconsenti.domain.model.LegalBase;
import br.com.vertzon.gconsenti.domain.model.enumerator.JSFBeanEnum;
import br.com.vertzon.gconsenti.domain.repository.JsfLabelRepository;
import br.com.vertzon.gconsenti.domain.repository.LegalBaseRepository;
import br.com.vertzon.gconsenti.domain.service.LegalBaseService;

@Component
@Scope("view")
public class SearchLegalBaseBean implements Serializable {
	private static final long serialVersionUID = -4603180026903804948L;
	
	@Autowired
	private LegalBaseRepository legalBaseRepository;
	@Autowired
	private LegalBaseService legalBaseService;
	@Autowired
	private JsfLabelRepository jsfLabelRepository;
	
	private List<LegalBase> legalBases;
	private LegalBase legalBase;
	
	private List<JsfLabel> legalBaseObjects = new ArrayList<>();
	private List<JsfLabel> genericObjects = new ArrayList<>();
	private List<String> legalBaseLabels = new ArrayList<>();
	private List<String> genericLabels = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		legalBaseObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.LEGAL_BASE_SRC);
		genericObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.GENERIC_SRC);
		
		for (JsfLabel legalBaseLabel : legalBaseObjects) {
			legalBaseLabels.add(legalBaseLabel.getLabel());
		}
		
		for (JsfLabel genericLabel : genericObjects) {
			genericLabels.add(genericLabel.getLabel());
		}
		
		this.clear();
	}
	
	public void search() {
		if (this.legalBase != null) {
			this.legalBases = this.legalBaseService.search(legalBase);
		} else {
			this.clear();
		}
		
		this.legalBase = new LegalBase();
	}
	
	public void clear() {
		this.legalBases = this.legalBaseRepository.findAll();
		this.legalBase = new LegalBase();
	}
	
	public List<LegalBase> getLegalBases() {
		return legalBases;
	}
	public void setLegalBases(List<LegalBase> legalBases) {
		this.legalBases = legalBases;
	}

	public LegalBase getLegalBase() {
		return legalBase;
	}
	public void setLegalBase(LegalBase legalBase) {
		this.legalBase = legalBase;
	}

	public List<String> getLegalBaseLabels() {
		return legalBaseLabels;
	}

	public void setLegalBaseLabels(List<String> legalBaseLabels) {
		this.legalBaseLabels = legalBaseLabels;
	}

	public List<String> getGenericLabels() {
		return genericLabels;
	}

	public void setGenericLabels(List<String> genericLabels) {
		this.genericLabels = genericLabels;
	}
	
}
