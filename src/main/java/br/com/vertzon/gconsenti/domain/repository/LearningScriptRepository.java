package br.com.vertzon.gconsenti.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.vertzon.gconsenti.domain.model.LearningScript;

@Repository
public interface LearningScriptRepository extends JpaRepository<LearningScript, Long> {

	@Query(value = "select ls.code from LearningScript ls " + "inner join PersonalDataType pdt "
			+ "on ls.personalDataType = pdt.id " + "where pdt.id = :id")
	public String findCodeByPersonalDataType(@Param("id") Long id);
	
}
