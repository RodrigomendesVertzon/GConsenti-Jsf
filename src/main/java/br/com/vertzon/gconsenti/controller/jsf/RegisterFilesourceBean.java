package br.com.vertzon.gconsenti.controller.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.exception.BusinessRuleException;
import br.com.vertzon.gconsenti.domain.model.Filesource;
import br.com.vertzon.gconsenti.domain.model.JsfLabel;
import br.com.vertzon.gconsenti.domain.model.LegalBaseFinality;
import br.com.vertzon.gconsenti.domain.model.enumerator.JSFBeanEnum;
import br.com.vertzon.gconsenti.domain.repository.JsfLabelRepository;
import br.com.vertzon.gconsenti.domain.repository.LegalBaseFinalityRepository;
import br.com.vertzon.gconsenti.domain.service.FilesourceService;
import br.com.vertzon.gconsenti.util.jsf.FacesUtil;

@Component
@ViewScoped
public class RegisterFilesourceBean implements Serializable {
	private static final long serialVersionUID = -6374155541786676362L;

	@Autowired
	private FilesourceService filesourceService;
	@Autowired
	private LegalBaseFinalityRepository legalBaseFinalityRepository;
	@Autowired
	private RouterBean router;
	@Autowired
	private JsfLabelRepository jsfLabelRepository;
	
	private Filesource filesource; 
	private List<LegalBaseFinality> legalBaseFinalities;
	private List<String> labels = new ArrayList<String>();
	private List<JsfLabel> jsfLabels = new ArrayList<>();

	
	@PostConstruct
	public void init() {
		this.filesource = new Filesource();
		this.legalBaseFinalities = this.legalBaseFinalityRepository.findAll();
		jsfLabels = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.FILESOURCE_REG);
		
		for (JsfLabel jsfLabel : jsfLabels) {
			labels.add(jsfLabel.getLabel());
		}
	}
	
	public void register() throws BusinessRuleException {		
		try {
			this.filesourceService.register(filesource);
			FacesUtil.submitSuccess("Seu diretório de arquivos foi cadastrado com sucesso!", router.getSearchFilesourcesPage());
		} catch (BusinessRuleException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	

	public Filesource getFilesource() {
		return filesource;
	}

	public void setFilesource(Filesource filesource) {
		this.filesource = filesource;
	}

	public List<LegalBaseFinality> getLegalBaseFinalities() {
		return legalBaseFinalities;
	}

	public void setLegalBaseFinalities(List<LegalBaseFinality> legalBaseFinalities) {
		this.legalBaseFinalities = legalBaseFinalities;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}	
	
	
}
