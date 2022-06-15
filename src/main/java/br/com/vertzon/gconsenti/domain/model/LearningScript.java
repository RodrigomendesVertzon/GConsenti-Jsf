package br.com.vertzon.gconsenti.domain.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/*********************************************************************************
*********************** WARNING WARNING WARNING WARNING **************************
*********************************************************************************/
// 			NASHORN JAVASCRIPT ENGINE WILL BE REMOVED ON JDK 11 ONWARDS
// 			NASHORN JAVASCRIPT ENGINE WILL BE REMOVED ON JDK 11 ONWARDS
// 			NASHORD JAVASCRIPT ENGINE WILL BE REMOVED ON JDK 11 ONWARDS
/*********************************************************************************
*********************** WARNING WARNING WARNING WARNING **************************
*********************************************************************************/

@Entity
@Table(name = "learning_scripts")
public class LearningScript implements Serializable {

	private static final long serialVersionUID = 977540960349260681L;

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
	@Column(name = "code", length = 5000)
	private String code;

	@NonNull
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_personal_data_type")
	private PersonalDataType personalDataType;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public PersonalDataType getPersonalDataType() {
		return personalDataType;
	}

	public void setPersonalDataType(PersonalDataType personalDataType) {
		this.personalDataType = personalDataType;
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
		LearningScript other = (LearningScript) obj;
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
