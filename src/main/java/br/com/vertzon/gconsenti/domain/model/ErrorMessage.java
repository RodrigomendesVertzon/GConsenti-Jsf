package br.com.vertzon.gconsenti.domain.model;

import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="error_messages")
public class ErrorMessage {

/////////////////////////////////////////////////////
///////////////////////////// ATTRIBUTES
/////////////////////////////////////////////////////
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Nonnull
	@Column(name="error_code", unique = true)
	private String errorCode;

	@Nonnull
	@Column(name="label")
	private String label;
	
	@Nonnull
	@Column(name="desc_error")
	private String descError;
	
	

/////////////////////////////////////////////////////
///////////////////////////// GETTERS & SETTERS
/////////////////////////////////////////////////////
	
	
	
	
	public String getLabel() {
		return label;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescError() {
		return descError;
	}

	public void setDescError(String descError) {
		this.descError = descError;
	}
	
	public String getCompleteError() {
		return getLabel() + ": " + getDescError();
	}
}
