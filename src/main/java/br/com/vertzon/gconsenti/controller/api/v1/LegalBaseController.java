package br.com.vertzon.gconsenti.controller.api.v1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vertzon.gconsenti.domain.model.LegalBase;
import br.com.vertzon.gconsenti.domain.repository.LegalBaseRepository;

@RestController
@RequestMapping("/api/v1/legalbases")
public class LegalBaseController {

	@Autowired
	private LegalBaseRepository legalBaseRepository;

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<LegalBase> legalBases = legalBaseRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(legalBases);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable(value = "id", required = true) Long id) {
		Optional<LegalBase> legalBase = legalBaseRepository.findById(id);
		if (legalBase.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(legalBase);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
