package br.com.vertzon.gconsenti.domain.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "filesources")
public class Filesource implements Serializable {

	private static final long serialVersionUID = 7407130774044916582L;

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
	@Column(name = "filepath")
	private String filePath;

	@Nullable
	@Column(name = "username")
	private String username;

	@Nullable
	@Column(name = "password")
	private String password;

	@NonNull
	@ManyToMany(cascade = CascadeType.REFRESH)
	@JoinTable(name = "filesource_legal_base_finalities", joinColumns = @JoinColumn(name = "id_filesource"), inverseJoinColumns = @JoinColumn(name = "id_legal_base_finality"))
	private List<LegalBaseFinality> legalBaseFinalities;
	
	@Transient
	private transient boolean editing;
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<LegalBaseFinality> getLegalBaseFinalities() {
		return legalBaseFinalities;
	}

	public void setLegalBaseFinalities(List<LegalBaseFinality> legalBaseFinalities) {
		this.legalBaseFinalities = legalBaseFinalities;
	}
	
	public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
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
		Filesource other = (Filesource) obj;
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
