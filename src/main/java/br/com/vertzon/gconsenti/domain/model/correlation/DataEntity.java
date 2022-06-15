package br.com.vertzon.gconsenti.domain.model.correlation;

import java.util.Objects;

import org.springframework.security.core.Transient;

@Transient
public class DataEntity {
	
	private String aid;
	private String bid;
	private String cid;
	
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
		DataEntity other = (DataEntity) obj;
		return Objects.equals(aid, other.aid) && Objects.equals(bid, other.bid) && Objects.equals(cid, other.cid);
	}
}
