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
import br.com.vertzon.gconsenti.domain.model.LearningData;
import br.com.vertzon.gconsenti.domain.repository.LearningDataRepository;
import br.com.vertzon.gconsenti.domain.service.LearningDataService;

@RestController
@RequestMapping("/api/v1/machinelearningdatasources")
public class LearningDataController {
	

	@Autowired
	private LearningDataRepository learningDataRepository;
	@Autowired
	private LearningDataService learningDataService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<LearningData> learningDatas = learningDataRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(learningDatas);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable(value = "id", required = true) Long id) {
		Optional<LearningData> learningData = learningDataRepository.findById(id);
		if (learningData.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(learningData);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("/personaldatatypes/{id}")
	public ResponseEntity<?> getMachineLearningLearningDataByPersonalDataTypeId(@PathVariable(value = "id", required = true) Long id) {
		LearningData learningData = learningDataRepository.findLearningDatasourceByPersonalDataType(id);
		if (learningData != null) {
			return ResponseEntity.status(HttpStatus.OK).body(learningData);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PostMapping
	public ResponseEntity<?> register(@RequestBody LearningData learningData) {
		try {
			learningDataService.register(learningData);
			return ResponseEntity.status(HttpStatus.CREATED).body(learningData);
		} catch (BusinessRuleException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			learningDataService.delete(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
}
