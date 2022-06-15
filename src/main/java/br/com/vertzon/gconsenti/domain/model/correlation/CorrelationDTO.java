package br.com.vertzon.gconsenti.domain.model.correlation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_EMPTY)
public class CorrelationDTO {
	
	private String datasourceName;
	private String tableName;
	private String columnName;
	private String personalDataLocationId;
	private String cValue;
	private String rValue;
	private String nValue;
	private String oValue;
	
	public String getDatasourceName() {
		return datasourceName;
	}
	public void setDatasourceName(String datasourceName) {
		this.datasourceName = datasourceName;
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
	public String getPersonalDataLocationId() {
		return personalDataLocationId;
	}
	public void setPersonalDataLocationId(String personalDataLocationId) {
		this.personalDataLocationId = personalDataLocationId;
	}
	public String getcValue() {
		return cValue;
	}
	public void setcValue(String cValue) {
		this.cValue = cValue;
	}
	public String getrValue() {
		return rValue;
	}
	public void setrValue(String rValue) {
		this.rValue = rValue;
	}
	public String getnValue() {
		return nValue;
	}
	public void setnValue(String nValue) {
		this.nValue = nValue;
	}
	public String getoValue() {
		return oValue;
	}
	public void setoValue(String oValue) {
		this.oValue = oValue;
	}
	
	@Override
	public String toString() {
		return "CorrelationDTO [datasourceName=" + datasourceName + ", tableName=" + tableName + ", columnName="
				+ columnName + ", personalDataLocationId=" + personalDataLocationId + ", cValue=" + cValue + ", rValue="
				+ rValue + ", nValue=" + nValue + ", oValue=" + oValue + "]";
	}
}
