package br.com.vertzon.gconsenti.domain.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "legal_base_finalities")
public class LegalBaseFinality implements Serializable {

	private static final long serialVersionUID = 7015438808261663365L;

/////////////////////////////////////////////////////
///////////////////////////// ATTRIBUTES
/////////////////////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_finality")
	private Finality finality;

	@NonNull
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_legal_base")
	private LegalBase legalBase;
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

	public Finality getFinality() {
		return finality;
	}

	public void setFinality(Finality finality) {
		this.finality = finality;
	}

	public LegalBase getLegalBase() {
		return legalBase;
	}

	public void setLegalBase(LegalBase legalBase) {
		this.legalBase = legalBase;
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
		LegalBaseFinality other = (LegalBaseFinality) obj;
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
