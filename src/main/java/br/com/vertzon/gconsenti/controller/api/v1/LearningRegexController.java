package br.com.vertzon.gconsenti.controller.api.v1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vertzon.gconsenti.domain.exception.BusinessRuleException;
import br.com.vertzon.gconsenti.domain.exception.EntityInUseException;
import br.com.vertzon.gconsenti.domain.exception.EntityNotFoundException;
import br.com.vertzon.gconsenti.domain.model.LearningRegex;
import br.com.vertzon.gconsenti.domain.repository.LearningRegexRepository;
import br.com.vertzon.gconsenti.domain.service.LearningRegexService;

@RestController
@RequestMapping("/api/v1/regularexpressions")
public class LearningRegexController {

	@Autowired
	private LearningRegexRepository learningRegexRepository;
	@Autowired
	private LearningRegexService learningRegexService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<LearningRegex> learningRegexs = learningRegexRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(learningRegexs);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable(value = "id", required = true) Long id) {
		Optional<LearningRegex> learningRegex = learningRegexRepository.findById(id);
		if (learningRegex.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(learningRegex);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@GetMapping("/patterns/{id}")
	public ResponseEntity<?> getByPersonalDataTypeId(@PathVariable(value = "id", required = true) Long id) {
		String pattern = learningRegexRepository.findPatternByPersonalDataType(id);
		if (pattern != null) {
			return ResponseEntity.status(HttpStatus.OK).body(pattern);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PostMapping
	public ResponseEntity<?> register(@RequestBody LearningRegex learningRegex) {
		try {
			learningRegexService.register(learningRegex);
			return ResponseEntity.status(HttpStatus.CREATED).body(learningRegex);
		} catch (BusinessRuleException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			learningRegexService.delete(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
}
