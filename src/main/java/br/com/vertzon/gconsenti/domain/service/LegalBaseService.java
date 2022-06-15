package br.com.vertzon.gconsenti.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vertzon.gconsenti.domain.model.LegalBase;
import br.com.vertzon.gconsenti.domain.repository.LegalBaseRepository;

@Service
public class LegalBaseService {

	@Autowired
	private LegalBaseRepository legalBaseRepository;

	@Transactional(readOnly = true)
	public List<LegalBase> search(LegalBase legalBaseFilter) {
		Example<LegalBase> example = Example.of(legalBaseFilter,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return legalBaseRepository.findAll(example);
	}
}
