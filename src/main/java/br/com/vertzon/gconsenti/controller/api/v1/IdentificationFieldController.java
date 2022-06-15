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
import br.com.vertzon.gconsenti.domain.model.IdentificationField;
import br.com.vertzon.gconsenti.domain.repository.IdentificationFieldRepository;
import br.com.vertzon.gconsenti.domain.service.IdentificationFieldService;

@RestController
@RequestMapping("/api/v1/identificationfields")
public class IdentificationFieldController {

	@Autowired
	private IdentificationFieldRepository identificationFieldRepository;
	@Autowired
	private IdentificationFieldService identificationFieldService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<IdentificationField> identificationFields = identificationFieldRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(identificationFields);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable(value = "id", required = true) Long id) {
		Optional<IdentificationField> identificationField = identificationFieldRepository.findById(id);
		if (identificationField.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(identificationField);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping
	public ResponseEntity<?> register(@RequestBody IdentificationField identificationField) {
		try {
			identificationFieldService.register(identificationField);
			return ResponseEntity.status(HttpStatus.CREATED).body(identificationField);
		} catch (BusinessRuleException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			identificationFieldService.delete(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
}
