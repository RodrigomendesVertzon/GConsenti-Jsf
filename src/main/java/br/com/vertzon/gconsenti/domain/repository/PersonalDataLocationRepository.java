package br.com.vertzon.gconsenti.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.vertzon.gconsenti.domain.model.PersonalDataLocation;
import br.com.vertzon.gconsenti.domain.model.correlation.PersonalDataLocationView;

@Repository
public interface PersonalDataLocationRepository extends JpaRepository<PersonalDataLocation, Long> {

	@Query(value = "SELECT CASE WHEN (COUNT(pdl.id) > 0)  THEN true ELSE false END" + " FROM PersonalDataLocation pdl"
			+ "	WHERE pdl.datasourceName = :datasourceName" + " AND pdl.tableName = :tableName"
			+ " AND pdl.columnName = :columnName" + " AND pdl.personalDataTypeName = :personalDataTypeName")
	public boolean alreadyExists(String datasourceName, String tableName, String columnName,
			String personalDataTypeName);
	
	public List<PersonalDataLocation> findByDatasourceName(String datasourceName);
	
	public Optional<PersonalDataLocation> findByDatasourceNameAndTableNameAndColumnName(String datasourceName, String tableName, String columnName);

	// FOR DASHBOARD VIEW
	@Query(value = "SELECT COUNT(pdl.id), pdt.name from PersonalDataLocation pdl" + " INNER JOIN PersonalDataType pdt"
			+ "	ON pdl.personalDataType = pdt.id" + " GROUP BY pdt.name")
	public List<Object[]> findDataPerType();

	@Query(value = "SELECT COUNT(pdl.id), d.name from PersonalDataLocation pdl" + "	INNER JOIN Datasource d"
			+ "	ON pdl.datasource = d.id")
	public List<Object[]> findDataPerDatasource();

	@Query(value = "SELECT COUNT(DISTINCT pdl.personalDataType) from PersonalDataLocation pdl")
	public Long countPerType();
	
	@Query(value = "SELECT concat(datasourceName, '.', tableName, '.', columnName) FROM PersonalDataLocation pdt WHERE pdt.personalDataTypeName = 'CPF'")
	public List<String> findLinkedCpfs();
	
	@Query(value = "SELECT * FROM vw_other_correlation_fields", nativeQuery = true)
	public List<PersonalDataLocationView> findCorrelationFieldsView();
}
