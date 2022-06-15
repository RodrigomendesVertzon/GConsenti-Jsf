package br.com.vertzon.gconsenti.domain.model.correlation;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "n_data_entities")
public class NDataEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String aid;
	private String bid;
	private String cid;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(aid, bid, cid);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NDataEntity other = (NDataEntity) obj;
		return Objects.equals(aid, other.aid) && Objects.equals(bid, other.bid) && Objects.equals(cid, other.cid);
	}
	
	@Override
	public String toString() {
		return "NDataEntity [id=" + id + ", aid=" + aid + ", bid=" + bid + ", cid=" + cid + "]";
	}
}