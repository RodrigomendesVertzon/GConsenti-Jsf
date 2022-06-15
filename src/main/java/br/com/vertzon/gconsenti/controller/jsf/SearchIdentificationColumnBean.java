package br.com.vertzon.gconsenti.controller.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.exception.BusinessRuleException;
import br.com.vertzon.gconsenti.domain.model.IdentificationColumn;
import br.com.vertzon.gconsenti.domain.model.JsfLabel;
import br.com.vertzon.gconsenti.domain.model.enumerator.JSFBeanEnum;
import br.com.vertzon.gconsenti.domain.repository.IdentificationColumnRepository;
import br.com.vertzon.gconsenti.domain.repository.JsfLabelRepository;
import br.com.vertzon.gconsenti.domain.service.IdentificationColumnService;
import br.com.vertzon.gconsenti.util.jsf.FacesUtil;

@Component
@Scope("view")
public class SearchIdentificationColumnBean implements Serializable {
	private static final long serialVersionUID = -4603180026903804948L;
	
	@Autowired
	private IdentificationColumnRepository identificationColumnRepository;
	@Autowired
	private IdentificationColumnService identificationColumnService;
	@Autowired
	private JsfLabelRepository jsfLabelRepository;
	
	private List<IdentificationColumn> identificationColumns;
	private IdentificationColumn identificationColumn;
	
	private List<JsfLabel> identificationColumnObjects = new ArrayList<>();
	private List<JsfLabel> genericObjects = new ArrayList<>();
	private List<String> identificationColumnLabels = new ArrayList<>();
	private List<String> genericLabels = new ArrayList<>();
	
	
	@PostConstruct
	public void init() {
		identificationColumnObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.IDENTIFICATION_COLUMN_SRC);
		genericObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.GENERIC_SRC);
		
		for (JsfLabel identificationColumnLabel : identificationColumnObjects) {
			identificationColumnLabels.add(identificationColumnLabel.getLabel());
		}
		
		for (JsfLabel genericLabel : genericObjects) {
			genericLabels.add(genericLabel.getLabel());
		}
		
		this.clear();
	}
	
	public void search() {
		if (this.identificationColumn != null) {
			this.identificationColumns = this.identificationColumnService.search(identificationColumn);
		} else {
			this.clear();
		}
	}
	
	public void delete() {
		try {
			identificationColumnService.delete(identificationColumn.getId());
			identificationColumns.remove(identificationColumn);
		} catch (BusinessRuleException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void clear() {
		this.identificationColumns = this.identificationColumnRepository.findAll();
		this.identificationColumn = new IdentificationColumn();
	}
	
	
	
	public List<IdentificationColumn> getIdentificationColumns() {
		return identificationColumns;
	}
	public void setIdentificationColumns(List<IdentificationColumn> identificationColumns) {
		this.identificationColumns = identificationColumns;
	}

	public IdentificationColumn getIdentificationColumn() {
		return identificationColumn;
	}
	public void setIdentificationColumn(IdentificationColumn identificationColumn) {
		this.identificationColumn = identificationColumn;
	}

	public List<String> getIdentificationColumnLabels() {
		return identificationColumnLabels;
	}

	public void setIdentificationColumnLabels(List<String> identificationColumnLabels) {
		this.identificationColumnLabels = identificationColumnLabels;
	}

	public List<String> getGenericLabels() {
		return genericLabels;
	}

	public void setGenericLabels(List<String> genericLabels) {
		this.genericLabels = genericLabels;
	}
	
}
