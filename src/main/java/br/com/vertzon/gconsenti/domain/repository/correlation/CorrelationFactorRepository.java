package br.com.vertzon.gconsenti.domain.repository.correlation;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.vertzon.gconsenti.domain.model.correlation.CDataEntity;
import br.com.vertzon.gconsenti.domain.model.correlation.CorrelationFactor;
import br.com.vertzon.gconsenti.domain.model.correlation.NDataEntity;
import br.com.vertzon.gconsenti.domain.model.correlation.RDataEntity;

@Repository
public interface CorrelationFactorRepository extends JpaRepository<CorrelationFactor, Long> {
	
	public CorrelationFactor findBycDataEntity(CDataEntity cDataEntity);
	
	public CorrelationFactor findByrDataEntity(RDataEntity rDataEntity);
	
	public CorrelationFactor findBynDataEntity(NDataEntity nDataEntity);
	
	@Query(value = "SELECT columnName FROM PersonalDataLocation pdl "
		+ "WHERE pdl.personalDataTypeName like 'CPF' "
		+ "AND tableName like :tableName "
		+ "and datasource_name like :datasourceName")
	public String findCDataEntityByTableAndDatasource(
		@Param("tableName") String tableName, 
		@Param("datasourceName") String datasourceName
	);
	
	@Query(value = "SELECT cf.id FROM CorrelationFactor cf "
		+ "INNER JOIN CDataEntity cde "
		+ "ON cf.cDataEntity = cde.id "
		+ "WHERE cde.aid = :caid "
		+ "AND cde.bid = :cbid "
		+ "AND cde.cid = :ccid")
	public Long findFactorByC(
		@Param("caid") String caid, 
		@Param("cbid") String cbid,
		@Param("ccid") String ccid
	);
	
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query(value = "UPDATE CorrelationFactor cf "
		+ "SET cf.rDataEntity = :rDataEntity "
		+ "WHERE cf.id = :correlationFactorId")
	public void updateFactor(@Param("rDataEntity") RDataEntity rDataEntity, @Param("correlationFactorId") Long correlationFactorId);
	
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query(value = "UPDATE CorrelationFactor cf "
		+ "SET cf.nDataEntity = :nDataEntity "
		+ "WHERE cf.id = :correlationFactorId")
	public void updateFactor(@Param("nDataEntity") NDataEntity rDataEntity, @Param("correlationFactorId") Long correlationFactorId);

}
