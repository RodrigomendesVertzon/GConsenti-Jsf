package br.com.vertzon.gconsenti.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.vertzon.gconsenti.domain.model.LearningData;

@Repository
public interface LearningDataRepository extends JpaRepository<LearningData, Long> {

	@Query(value = "from LearningData ld " + "inner join PersonalDataType pdt " + "on ld.personalDataType = pdt.id "
			+ "where pdt.id = :id")
	public LearningData findLearningDatasourceByPersonalDataType(@Param("id") Long id);
	
}
