package br.com.vertzon.gconsenti.domain.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import br.com.vertzon.gconsenti.domain.model.enumerator.ForDataLearningEnum;

@Entity
@Table(name = "datasources")
public class Datasource implements Serializable {

	private static final long serialVersionUID = -174862245168283757L;

/////////////////////////////////////////////////////
///////////////////////////// ATTRIBUTES
/////////////////////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	@Column(name = "name")
	private String name;
	
	@NonNull
	@Column(name = "authorization_database")
	private String authorizationDatabase;

	@Nullable
	@Column(name = "description")
	private String description;

	@NonNull
	@Column(name = "url")
	private String url;

	@NonNull
	@Column(name = "ip")
	private String ip;

	@NonNull
	@Column(name = "port")
	private String port;

	@NonNull
	@Column(name = "username")
	private String username;

	@NonNull
	@Column(name = "password")
	private String password;

	@NonNull
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_database_type")
	private DatabaseType databaseType;

	@NonNull
	@ManyToMany(cascade = CascadeType.REFRESH)
	@JoinTable(name = "datasource_legal_base_finalities", joinColumns = @JoinColumn(name = "id_datasource"), inverseJoinColumns = @JoinColumn(name = "id_legal_base_finality"))
	private List<LegalBaseFinality> legalBaseFinalities;

	@NonNull
	@Column(name = "data_learning")
	@Enumerated
	private ForDataLearningEnum dataLearning;
	
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

	public String getAuthorizationDatabase() {
		return authorizationDatabase;
	}

	public void setAuthorizationDatabase(String authorizationDatabase) {
		this.authorizationDatabase = authorizationDatabase;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
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

	public DatabaseType getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(DatabaseType databaseType) {
		this.databaseType = databaseType;
	}

	public List<LegalBaseFinality> getLegalBaseFinalities() {
		return legalBaseFinalities;
	}

	public void setLegalBaseFinalities(List<LegalBaseFinality> legalBaseFinalities) {
		this.legalBaseFinalities = legalBaseFinalities;
	}

	public ForDataLearningEnum getDataLearning() {
		return dataLearning;
	}

	public void setDataLearning(ForDataLearningEnum dataLearning) {
		this.dataLearning = dataLearning;
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
		Datasource other = (Datasource) obj;
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
