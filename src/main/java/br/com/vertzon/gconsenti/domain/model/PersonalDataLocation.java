package br.com.vertzon.gconsenti.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import br.com.vertzon.gconsenti.domain.model.correlation.CorrelationView;

@Entity
@Table(name = "personal_data_locations")
@SqlResultSetMapping(name = "CorrelationView", classes = {
	@ConstructorResult(targetClass = CorrelationView.class, columns = { 
			@ColumnResult(name = "banco"),
			@ColumnResult(name = "tabela"), 
			@ColumnResult(name = "coluna"), 
			@ColumnResult(name = "nome")
	}) 
})
public class PersonalDataLocation implements Serializable {

	private static final long serialVersionUID = -9150019535187237087L;

/////////////////////////////////////////////////////
///////////////////////////// ATTRIBUTES
/////////////////////////////////////////////////////
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@Nullable
	@Column(name = "table_name")
	private String tableName;

	@Nullable
	@Column(name = "column_name")
	private String columnName;

	@Nullable
	@Column(name = "id_datasource")
	private String datasource;

	@Nullable
	@Column(name = "datasource_name")
	private String datasourceName;

	@Nullable
	@Column(name = "id_filesource")
	private String filesource;

	@NonNull
	@Column(name = "id_personal_data_type")
	private String personalDataType;

	@NonNull
	@Column(name = "personal_data_type_name")
	private String personalDataTypeName;

	@NonNull
	@Column(name = "ip")
	private String ip;

	@Nullable
	@Column(name = "driver")
	private String driver;

	
/////////////////////////////////////////////////////
///////////////////////////// GETTERS & SETTERS
/////////////////////////////////////////////////////
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getDatasource() {
		return datasource;
	}

	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}

	public String getFilesource() {
		return filesource;
	}

	public void setFilesource(String filesource) {
		this.filesource = filesource;
	}

	public String getPersonalDataType() {
		return personalDataType;
	}

	public void setPersonalDataType(String personalDataType) {
		this.personalDataType = personalDataType;
	}

	public String getDatasourceName() {
		return datasourceName;
	}

	public void setDatasourceName(String datasourceName) {
		this.datasourceName = datasourceName;
	}

	public String getPersonalDataTypeName() {
		return personalDataTypeName;
	}

	public void setPersonalDataTypeName(String personalDataTypeName) {
		this.personalDataTypeName = personalDataTypeName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}
}
