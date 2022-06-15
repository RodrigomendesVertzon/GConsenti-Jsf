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
import br.com.vertzon.gconsenti.domain.repository.LegalBaseRepository;
import br.com.vertzon.gconsenti.domain.service.LegalBaseFinalityService;
import br.com.vertzon.gconsenti.util.jsf.FacesUtil;

@Component
@Scope("view")
public class RegisterLegalBaseFinalityBean implements Serializable {
	private static final long serialVersionUID = 7274054190849026027L;

	@Autowired
	private LegalBaseFinalityService legalBaseFinalityService;
	@Autowired
	private LegalBaseRepository legalBaseRepository;
	@Autowired
	private FinalityRepository finalityRepository;
	@Autowired
	private RouterBean router;
	@Autowired
	private JsfLabelRepository jsfLabelRepository;

	private LegalBaseFinality legalBaseFinality;
	private List<LegalBase> legalBases;
	private List<Finality> finalities;
	private List<String> labels = new ArrayList<String>();
	private List<JsfLabel> jsfLabels = new ArrayList<>();

	@PostConstruct
	public void init() {
		this.legalBaseFinality = new LegalBaseFinality();
		this.legalBases = this.legalBaseRepository.findAll();
		this.finalities = this.finalityRepository.findAll();
		jsfLabels = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.LEGAL_BASE_FINALITY_REG);
		
		for (JsfLabel jsfLabel : jsfLabels) {
			labels.add(jsfLabel.getLabel());
		}
	}

	public void register() throws BusinessRuleException {
		try {
			this.legalBaseFinalityService.register(legalBaseFinality);
			FacesUtil.submitSuccess("A finalidade por base legal foi cadastrada com sucesso!",
					router.getSearchLegalBaseFinalitiesPage());
		} catch (BusinessRuleException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public LegalBaseFinality getLegalBaseFinality() {
		return legalBaseFinality;
	}

	public void setlegalBaseFinality(LegalBaseFinality legalBaseFinality) {
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

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	
}
