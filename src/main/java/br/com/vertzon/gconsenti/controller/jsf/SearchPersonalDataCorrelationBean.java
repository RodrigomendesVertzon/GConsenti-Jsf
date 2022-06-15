package br.com.vertzon.gconsenti.controller.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.model.correlation.CorrelationView;
import br.com.vertzon.gconsenti.domain.service.PersonalDataLocationService;

@Component
@Scope("view")
public class SearchPersonalDataCorrelationBean implements Serializable {
	private static final long serialVersionUID = -2323474186964612039L;

	private List<CorrelationView> correlations = new ArrayList<CorrelationView>();
	private String nome;
	private String cpf;
	private String rg;

	@Autowired
	private PersonalDataLocationService personalDataLocationService;

	public void search() {
		this.correlations = this.personalDataLocationService.getByNameOrRgOrCpf(nome, cpf, rg);
		nome = null;
		cpf = null;
		rg = null;
	}

	public List<CorrelationView> getCorrelations() {
		return correlations;
	}
	public void setCorrelations(List<CorrelationView> correlations) {
		this.correlations = correlations;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
}
