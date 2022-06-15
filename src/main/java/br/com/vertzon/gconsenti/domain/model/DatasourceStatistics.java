package br.com.vertzon.gconsenti.domain.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "datasource_statistics")
public class DatasourceStatistics implements Serializable {
	
	private static final long serialVersionUID = 1631038131999197150L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "data_read")
	private String dataRead;
	
	@Column(name = "data_identified")
	private String dataIdentified;
	
	@Column(name = "columns_identified")
	private String columnsIdentified;
	
	@NonNull
	@OneToOne(cascade = CascadeType.REFRESH)
	private Datasource datasource;

	public String getDataRead() {
		return dataRead;
	}
	public void setDataRead(String dataRead) {
		this.dataRead = dataRead;
	}
	public String getDataIdentified() {
		return dataIdentified;
	}
	public void setDataIdentified(String dataIdentified) {
		this.dataIdentified = dataIdentified;
	}
	public String getColumnsIdentified() {
		return columnsIdentified;
	}
	public void setColumnsIdentified(String columnsIdentified) {
		this.columnsIdentified = columnsIdentified;
	}
	public Datasource getDatasource() {
		return datasource;
	}
	public void setDatasource(Datasource datasource) {
		this.datasource = datasource;
	}
	
}
