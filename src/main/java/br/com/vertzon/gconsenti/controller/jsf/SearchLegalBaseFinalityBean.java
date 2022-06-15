package br.com.vertzon.gconsenti.controller.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.exception.BusinessRuleException;
import br.com.vertzon.gconsenti.domain.model.Finality;
import br.com.vertzon.gconsenti.domain.model.JsfLabel;
import br.com.vertzon.gconsenti.domain.model.LegalBase;
import br.com.vertzon.gconsenti.domain.model.LegalBaseFinality;
import br.com.vertzon.gconsenti.domain.model.enumerator.JSFBeanEnum;
import br.com.vertzon.gconsenti.domain.repository.FinalityRepository;
import br.com.vertzon.gconsenti.domain.repository.JsfLabelRepository;
import br.com.vertzon.gconsenti.domain.repository.LegalBaseFinalityRepository;
import br.com.vertzon.gconsenti.domain.repository.LegalBaseRepository;
import br.com.vertzon.gconsenti.domain.service.LegalBaseFinalityService;

@Component
@Scope("view")
public class SearchLegalBaseFinalityBean implements Serializable {
	private static final long serialVersionUID = -4603180026903804948L;
	
	@Autowired
	private LegalBaseFinalityRepository legalBaseFinalityRepository;
	@Autowired
	private LegalBaseFinalityService legalBaseFinalityService;
	@Autowired
	private LegalBaseRepository legalBaseRepository;
	@Autowired
	private FinalityRepository finalityRepository;
	@Autowired
	private JsfLabelRepository jsfLabelRepository;
	
	private List<LegalBaseFinality> legalBaseFinalities;
	private LegalBaseFinality legalBaseFinality;
	private List<LegalBase> legalBases;
	private List<Finality> finalities;
	
	private List<JsfLabel> legalBaseFinalityObjects = new ArrayList<>();
	private List<JsfLabel> genericObjects = new ArrayList<>();
	private List<String> legalBaseFinalityLabels = new ArrayList<>();
	private List<String> genericLabels = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		legalBaseFinalityObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.LEGAL_BASE_FINALITY_SRC);
		genericObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.GENERIC_SRC);
		
		for (JsfLabel legalBaseFinalityLabel : legalBaseFinalityObjects) {
			legalBaseFinalityLabels.add(legalBaseFinalityLabel.getLabel());
		}
		
		for (JsfLabel genericLabel : genericObjects) {
			genericLabels.add(genericLabel.getLabel());
		}
		
		this.clear();
	}
	
	public void search() {
		if (this.legalBaseFinality != null) {
			this.legalBaseFinalities = this.legalBaseFinalityService.search(legalBaseFinality);
		} else {
			this.clear();
		}
	}
	
	public void delete() {
		try {
			legalBaseFinalityService.delete(legalBaseFinality.getId());
			legalBaseFinalities.remove(legalBaseFinality);
		} catch (BusinessRuleException e) {
			e.getMessage();
		}
	}
	
	public void clear() {
		this.legalBaseFinalities = this.legalBaseFinalityRepository.findAll();
		this.legalBaseFinality = new LegalBaseFinality();
		this.legalBases = this.legalBaseRepository.findAll();
		this.finalities = this.finalityRepository.findAll();
	}
	
	
	
	public List<LegalBaseFinality> getLegalBaseFinalities() {
		return legalBaseFinalities;
	}
	public void setLegalBaseFinalities(List<LegalBaseFinality> legalBaseFinalities) {
		this.legalBaseFinalities = legalBaseFinalities;
	}

	public LegalBaseFinality getLegalBaseFinality() {
		return legalBaseFinality;
	}
	public void setLegalBaseFinality(LegalBaseFinality legalBaseFinality) {
		this.legalBaseFinality = legalBaseFinality;
	}

	public List<LegalBase> getLegalBases() {
		return legalBases;
	}
	public void setLegalBases(List<LegalBase> legalBases) {
		this.legalBases = legalBases;
	}

	public List<Finality> getFinalities() {
		return finalities;
	}
	public void setFinalities(List<Finality> finalities) {
		this.finalities = finalities;
	}

	public List<String> getLegalBaseFinalityLabels() {
		return legalBaseFinalityLabels;
	}

	public void setLegalBaseFinalityLabels(List<String> legalBaseFinalityLabels) {
		this.legalBaseFinalityLabels = legalBaseFinalityLabels;
	}

	public List<String> getGenericLabels() {
		return genericLabels;
	}

	public void setGenericLabels(List<String> genericLabels) {
		this.genericLabels = genericLabels;
	}
	
}
