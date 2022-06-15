package br.com.vertzon.gconsenti.controller.jsf;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.model.PersonalDataLocation;
import br.com.vertzon.gconsenti.domain.repository.PersonalDataLocationRepository;
import br.com.vertzon.gconsenti.domain.service.PersonalDataLocationService;

@Component
@Scope("view")
public class SearchPersonalDataLocationBean implements Serializable {
	private static final long serialVersionUID = -4603180026903804948L;

	@Autowired
	private PersonalDataLocationRepository personalDataLocationRepository;
	@Autowired
	private PersonalDataLocationService personalDataLocationService;
	
	private List<PersonalDataLocation> personalDataLocations;
	private PersonalDataLocation personalDataLocation;
	private String datasourceName;
	
	@PostConstruct
	public void init() {
		this.clear();
	}

	public void search() {
		if (this.personalDataLocation != null) {
			this.personalDataLocations = this.personalDataLocationService.search(datasourceName);
		} else {
			this.clear();
		}
	}

	public void clear() {
		this.personalDataLocations = this.personalDataLocationRepository.findAll();
		this.personalDataLocation = new PersonalDataLocation();
	}

	public List<PersonalDataLocation> getPersonalDataLocations() {
		return personalDataLocations;
	}

	public void setPersonalDataLocations(List<PersonalDataLocation> personalDataLocations) {
		this.personalDataLocations = personalDataLocations;
	}

	public PersonalDataLocation getPersonalDataLocation() {
		return personalDataLocation;
	}

	public void setPersonalDataLocation(PersonalDataLocation personalDataLocation) {
		this.personalDataLocation = personalDataLocation;
	}

	public String getDatasourceName() {
		return datasourceName;
	}

	public void setDatasourceName(String datasourceName) {
		this.datasourceName = datasourceName;
	}
}
