package br.com.vertzon.gconsenti.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.vertzon.gconsenti.domain.model.LearningRegex;

@Repository
public interface LearningRegexRepository extends JpaRepository<LearningRegex, Long> {

	@Query(value = "select lr.pattern from LearningRegex lr " + "inner join PersonalDataType pdt "
			+ "on lr.personalDataType = pdt.id " + "where pdt.id = :id")
	public String findPatternByPersonalDataType(@Param("id") Long id);
}
