package br.com.vertzon.gconsenti.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vertzon.gconsenti.domain.model.JsfLabel;
import br.com.vertzon.gconsenti.domain.model.enumerator.JSFBeanEnum;

public interface JsfLabelRepository extends JpaRepository<JsfLabel, Long> {

	JsfLabel findByJsfCode(String jsfCode);
	
	List<JsfLabel> findAllByJsfBean(JSFBeanEnum jsfBean);
	
}
