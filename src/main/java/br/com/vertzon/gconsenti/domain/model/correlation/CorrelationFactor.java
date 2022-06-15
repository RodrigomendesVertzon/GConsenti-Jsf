package br.com.vertzon.gconsenti.domain.model.correlation;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "correlation_factors")
public class CorrelationFactor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne
	@JoinColumn(name = "r_data_entity_id")
	private RDataEntity rDataEntity;
	@OneToOne
	@JoinColumn(name = "c_data_entity_id", unique = true)
	private CDataEntity cDataEntity;
	@OneToOne
	@JoinColumn(name = "n_data_entity_id")
	private NDataEntity nDataEntity;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public RDataEntity getRDataEntity() {
		return rDataEntity;
	}
	public void setRDataEntity(RDataEntity rDataEntity) {
		this.rDataEntity = rDataEntity;
	}
	public CDataEntity getCDataEntity() {
		return cDataEntity;
	}
	public void setCDataEntity(CDataEntity cDataEntity) {
		this.cDataEntity = cDataEntity;
	}
	public NDataEntity getNDataEntity() {
		return nDataEntity;
	}
	public void setNDataEntity(NDataEntity nDataEntity) {
		this.nDataEntity = nDataEntity;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CorrelationFactor other = (CorrelationFactor) obj;
		return Objects.equals(id, other.id);
	}
}
