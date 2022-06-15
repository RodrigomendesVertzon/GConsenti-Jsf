package br.com.vertzon.gconsenti.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import br.com.vertzon.gconsenti.domain.model.enumerator.IdentificationMethodEnum;
import br.com.vertzon.gconsenti.domain.model.enumerator.PersonalDataCategorizationEnum;

@Entity
@Table(name = "personal_data_types")
public class PersonalDataType implements Serializable {

	private static final long serialVersionUID = 2665095114924816745L;

/////////////////////////////////////////////////////
///////////////////////////// ATTRIBUTES
/////////////////////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	@Column(name = "name")
	private String name;

	@Nullable
	@Column(name = "description")
	private String description;

	@NonNull
	@Enumerated
	@Column(name = "method")
	private IdentificationMethodEnum method;

	@NonNull
	@Enumerated
	@Column(name = "category")
	private PersonalDataCategorizationEnum category;
	
	@NonNull
	@OneToOne(mappedBy = "personalDataType", orphanRemoval = true)
	private IdentificationField identificationField;
	
	@NonNull
	@OneToOne(mappedBy = "personalDataType", orphanRemoval = true)
	private LearningRegex learningRegex;
	
	@NonNull
	@OneToOne(mappedBy = "personalDataType", orphanRemoval = true)
	private LearningScript learningScript;
	
	@NonNull
	@OneToOne(mappedBy = "personalDataType", orphanRemoval = true)
	private LearningData learningData;
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////
///////////////////////////// GETTERS & SETTERS
/////////////////////////////////////////////////////
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public IdentificationMethodEnum getMethod() {
		return method;
	}

	public void setMethod(IdentificationMethodEnum method) {
		this.method = method;
	}

	public PersonalDataCategorizationEnum getCategory() {
		return category;
	}

	public void setCategory(PersonalDataCategorizationEnum category) {
		this.category = category;
	}
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////
///////////////////////////// HASHCODE & EQUALS
/////////////////////////////////////////////////////
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonalDataType other = (PersonalDataType) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
}
