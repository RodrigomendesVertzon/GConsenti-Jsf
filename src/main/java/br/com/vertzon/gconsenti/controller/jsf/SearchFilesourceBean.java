package br.com.vertzon.gconsenti.controller.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.exception.BusinessRuleException;
import br.com.vertzon.gconsenti.domain.model.Filesource;
import br.com.vertzon.gconsenti.domain.model.JsfLabel;
import br.com.vertzon.gconsenti.domain.model.enumerator.JSFBeanEnum;
import br.com.vertzon.gconsenti.domain.repository.FilesourceRepository;
import br.com.vertzon.gconsenti.domain.repository.JsfLabelRepository;
import br.com.vertzon.gconsenti.domain.service.FilesourceService;
import br.com.vertzon.gconsenti.util.jsf.FacesUtil;

@Component
@Scope("view")
public class SearchFilesourceBean implements Serializable {
	private static final long serialVersionUID = -4819889884187342728L;

	@Autowired
	private FilesourceService filesourceService;
	@Autowired
	private FilesourceRepository filesourceRepository;
	@Autowired
	private JsfLabelRepository jsfLabelRepository;

	private List<Filesource> filesources;
	private Filesource filesource;

	private List<JsfLabel> filesourceObjects = new ArrayList<>();
	private List<JsfLabel> genericObjects = new ArrayList<>();
	private List<String> filesourceLabels = new ArrayList<>();
	private List<String> genericLabels = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		filesourceObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.FILESOURCE_SRC);
		genericObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.GENERIC_SRC);
		
		for (JsfLabel filesourceLabel : filesourceObjects) {
			filesourceLabels.add(filesourceLabel.getLabel());
		}
		
		for (JsfLabel genericLabel : genericObjects) {
			genericLabels.add(genericLabel.getLabel());
		}
		
		this.clear();
	}

	public void search() {
		if (this.filesource != null) {
			this.filesources = this.filesourceService.search(filesource);
		} else {
			this.clear();
		}
	}

	public void delete() {
		try {
			filesourceService.delete(filesource.getId());
			filesources.remove(filesource);
		} catch (BusinessRuleException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void clear() {
		this.filesources = this.filesourceRepository.findAll();
		this.filesource = new Filesource();
	}

	public List<Filesource> getFilesources() {
		return filesources;
	}

	public void setFilesources(List<Filesource> filesources) {
		this.filesources = filesources;
	}

	public Filesource getFilesource() {
		return filesource;
	}

	public void setFilesource(Filesource filesource) {
		this.filesource = filesource;
	}

	public List<String> getFilesourceLabels() {
		return filesourceLabels;
	}

	public void setFilesourceLabels(List<String> filesourceLabels) {
		this.filesourceLabels = filesourceLabels;
	}

	public List<String> getGenericLabels() {
		return genericLabels;
	}

	public void setGenericLabels(List<String> genericLabels) {
		this.genericLabels = genericLabels;
	}

}
