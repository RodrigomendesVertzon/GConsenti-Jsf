package br.com.vertzon.gconsenti.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.vertzon.gconsenti.domain.model.ErrorMessage;

@Repository
public interface ErrorMessageRepository extends JpaRepository<ErrorMessage, Long>{

	@Query(value = "SELECT 'Error Code: '+ e.error_code +'  '+ e.label +': '+ e.desc_error FROM error_messages e WHERE e.error_code = ?1", nativeQuery = true)
	String findByErrorCode(String s);
}
