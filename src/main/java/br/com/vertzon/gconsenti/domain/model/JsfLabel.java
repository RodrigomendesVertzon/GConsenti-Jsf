package br.com.vertzon.gconsenti.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import br.com.vertzon.gconsenti.domain.model.enumerator.JSFBeanEnum;

@Entity
@Table(name = "jsf_label")
public class JsfLabel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	@Column(name = "label")
	private String label;
	
	@Column(name = "jsf_bean", nullable = false)
	@Enumerated(EnumType.STRING)
	private JSFBeanEnum jsfBean;

	@Column(name = "jsf_code", nullable = false, unique = true)
	private String jsfCode;

///////////////////////////////////////////////////////////////////	
///////////////////////////////////////////////////////////////////	
///////////////////////////////////////////////////////////////////	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public JSFBeanEnum getJsfBean() {
		return jsfBean;
	}

	public void setJsfBean(JSFBeanEnum jsfBean) {
		this.jsfBean = jsfBean;
	}

	public String getJsfCode() {
		return jsfCode;
	}

	public void setJsfCode(String jsfCode) {
		this.jsfCode = jsfCode;
	}
	

	
}
