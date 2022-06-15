package br.com.vertzon.gconsenti.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.vertzon.gconsenti.domain.model.enumerator.ScanScheduleIntervalEnum;
import br.com.vertzon.gconsenti.domain.model.enumerator.ScanScheduleStatus;

@Entity
@Table(name = "scan_schedules")
public class ScanSchedule implements Serializable {

	private static final long serialVersionUID = -3607143512216520920L;

/////////////////////////////////////////////////////
///////////////////////////// ATTRIBUTES
/////////////////////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Nullable
	@Column(name = "description")
	private String description;

	@Nullable
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_datasource")
	private Datasource datasource;

	@Nullable
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_filesource")
	private Filesource filesource;

	@NonNull
	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private ScanScheduleIntervalEnum type;

	@NonNull
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "datetime")
	private LocalDateTime datetime;

	@NonNull
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private ScanScheduleStatus status;
	
	@JsonIgnore
	@Transient
	private transient boolean editing;

	@JsonIgnore
	@Transient
	private transient String stringDate;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Datasource getDatasource() {
		return datasource;
	}

	public void setDatasource(Datasource datasource) {
		this.datasource = datasource;
	}

	public Filesource getFilesource() {
		return filesource;
	}

	public void setFilesource(Filesource filesource) {
		this.filesource = filesource;
	}

	public ScanScheduleIntervalEnum getType() {
		return type;
	}

	public void setType(ScanScheduleIntervalEnum type) {
		this.type = type;
	}

	public LocalDateTime getDatetime() {
		return datetime;
	}

	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}

	public ScanScheduleStatus getStatus() {
		return status;
	}

	public void setStatus(ScanScheduleStatus status) {
		this.status = status;
	}

	public String getStringDate() {
		return stringDate;
	}

	public void setStringDate(String stringDate) {
		this.stringDate = stringDate;
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
		ScanSchedule other = (ScanSchedule) obj;
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
