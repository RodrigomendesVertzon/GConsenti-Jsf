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
import br.com.vertzon.gconsenti.domain.model.enumerator.JSFBeanEnum;
import br.com.vertzon.gconsenti.domain.repository.FinalityRepository;
import br.com.vertzon.gconsenti.domain.repository.JsfLabelRepository;
import br.com.vertzon.gconsenti.domain.service.FinalityService;
import br.com.vertzon.gconsenti.util.jsf.FacesUtil;

@Component
@Scope("view")
public class SearchFinalityBean implements Serializable {
	private static final long serialVersionUID = -4603180026903804948L;

	@Autowired
	private FinalityService finalityService;
	@Autowired
	private FinalityRepository finalityRepository;
	@Autowired
	private JsfLabelRepository jsfLabelRepository;
	
	private List<Finality> finalities;
	private Finality finality;
	private Finality selectedFinality;

	private List<JsfLabel> finalityObjects = new ArrayList<>();
	private List<JsfLabel> genericObjects = new ArrayList<>();
	private List<String> finalityLabels = new ArrayList<>();
	private List<String> genericLabels = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		finalityObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.FINALITY_SRC);
		genericObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.GENERIC_SRC);
		
		for (JsfLabel finalityLabel : finalityObjects) {
			finalityLabels.add(finalityLabel.getLabel());
		}
		
		for (JsfLabel genericLabel : genericObjects) {
			genericLabels.add(genericLabel.getLabel());
		}
		
		this.clear();
	}

	public void clear() {
		this.finalities = this.finalityRepository.findAll();
		this.finality = new Finality();
		this.selectedFinality = new Finality();
	}

	public void search() {
		if (this.finality != null) {
			this.finalities = this.finalityService.search(finality);
		} else {
			this.clear();
		}
	}

	public void delete() {
		try {
			finalityService.delete(finality.getId());
			finalities.remove(finality);
		} catch (BusinessRuleException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public List<Finality> getFinalities() {
		return finalities;
	}

	public void setFinalities(List<Finality> finalities) {
		this.finalities = finalities;
	}

	public Finality getFinality() {
		return finality;
	}

	public void setFinality(Finality finality) {
		this.finality = finality;
	}

	public Finality getSelectedFinality() {
		return selectedFinality;
	}

	public void setSelectedFinality(Finality selectedFinality) {
		this.selectedFinality = selectedFinality;
	}

	public List<String> getFinalityLabels() {
		return finalityLabels;
	}

	public void setFinalityLabels(List<String> finalityLabels) {
		this.finalityLabels = finalityLabels;
	}

	public List<String> getGenericLabels() {
		return genericLabels;
	}

	public void setGenericLabels(List<String> genericLabels) {
		this.genericLabels = genericLabels;
	}
	
}
