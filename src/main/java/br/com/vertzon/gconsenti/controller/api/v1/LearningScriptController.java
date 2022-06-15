package br.com.vertzon.gconsenti.controller.api.v1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
import br.com.vertzon.gconsenti.domain.model.LearningScript;
import br.com.vertzon.gconsenti.domain.repository.LearningScriptRepository;
import br.com.vertzon.gconsenti.domain.service.LearningScriptService;

@RestController
@RequestMapping("/api/v1/scripts")
@Secured("ADMINISTRATOR")
public class LearningScriptController {

	@Autowired
	private LearningScriptRepository learningScriptRepository;
	@Autowired
	private LearningScriptService learningScriptService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<LearningScript> learningScripts = learningScriptRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(learningScripts);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable(value = "id", required = true) Long id) {
		Optional<LearningScript> learningScript = learningScriptRepository.findById(id);
		if (learningScript.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(learningScript);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("/codes/{id}")
	public ResponseEntity<?> getByPersonalDataTypeId(@PathVariable(value = "id", required = true) Long id) {
		String code = learningScriptRepository.findCodeByPersonalDataType(id);
		if (code != null) {
			return ResponseEntity.status(HttpStatus.OK).body(code);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PostMapping
	public ResponseEntity<?> register(@RequestBody LearningScript learningScript) {
		try {
			learningScriptService.register(learningScript);
			return ResponseEntity.status(HttpStatus.CREATED).body(learningScript);
		} catch (BusinessRuleException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			learningScriptService.delete(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
}
