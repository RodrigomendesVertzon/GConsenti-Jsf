package br.com.vertzon.gconsenti.domain.repository.correlation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.vertzon.gconsenti.domain.model.correlation.CorrelationEntity;
import br.com.vertzon.gconsenti.domain.model.correlation.CorrelationFactor;

@Repository
public interface CorrelationEntityRepository extends JpaRepository<CorrelationEntity, Long>{
	
	@Query(value = 
		"FROM CorrelationEntity ce "
		+ "INNER JOIN PersonalDataLocation pdl "
		+ "ON ce.personalDataLocation = pdl.id "
		+ "WHERE ce.correlationFactor = :correlationFactor "
		+ "AND pdl.columnName = :columnName "
		+ "AND pdl.tableName = :tableName "
		+ "AND pdl.datasourceName = :datasourceName"
	) public CorrelationEntity findCorrelationEntityByCorrelationFactor(
		@Param("correlationFactor") CorrelationFactor correlationFactor,
		@Param("columnName") String columnName,
		@Param("tableName") String tableName,
		@Param("datasourceName") String datasourceName
	);
	
}
