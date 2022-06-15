package br.com.vertzon.gconsenti.controller.api.v1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
import br.com.vertzon.gconsenti.domain.model.PersonalDataLocation;
import br.com.vertzon.gconsenti.domain.model.correlation.PersonalDataLocationView;
import br.com.vertzon.gconsenti.domain.repository.PersonalDataLocationRepository;
import br.com.vertzon.gconsenti.domain.service.PersonalDataLocationService;

@RestController
@RequestMapping("/api/v1/personaldatalocations")
public class PersonalDataLocationController {

	@Autowired
	private PersonalDataLocationRepository personalDataLocationRepository;
	@Autowired
	private PersonalDataLocationService personalDataLocationService;
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		List<PersonalDataLocation> personalDataLocations = personalDataLocationRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(personalDataLocations);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable(value = "id", required = true) Long id) {
		Optional<PersonalDataLocation> personalDataLocation = personalDataLocationRepository.findById(id);
		if (personalDataLocation.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(personalDataLocation);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@GetMapping("/exists")
	public ResponseEntity<?> getIfAlreadyExists(@Param("datasourceName") String datasourceName,
			@Param("tableName") String tableName, @Param("columnName") String columnName,
			@Param("personalDataTypeName") String personalDataTypeName) {
		boolean result = personalDataLocationService.checkIfAlreadyExists(datasourceName, tableName, columnName,
				personalDataTypeName);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@GetMapping("/datasources")
	public ResponseEntity<List<PersonalDataLocation>> getByDatasourceName(@Param("datasourceName") String datasourceName) {
		List<PersonalDataLocation> personalDataLocations = personalDataLocationRepository.findByDatasourceName(datasourceName);
		return ResponseEntity.status(HttpStatus.OK).body(personalDataLocations);
	}
	
	@GetMapping("findOne")
	public ResponseEntity<String> getByDatasourceAndTableAndColumn(@Param("datasourceName") String datasourceName, 
			@Param("tableName") String tableName, @Param("columnName") String columnName) {
		Optional<PersonalDataLocation> personalDataLocation = personalDataLocationRepository
				.findByDatasourceNameAndTableNameAndColumnName(datasourceName, tableName, columnName);
		if (personalDataLocation.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(String.valueOf(personalDataLocation.get().getId()));
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping("/correlation/view")
	public ResponseEntity<List<PersonalDataLocationView>> getPersonalDataLocationView() {
		List<PersonalDataLocationView> personalDataLocationViewRepository = 
				personalDataLocationRepository.findCorrelationFieldsView();
		return ResponseEntity.status(HttpStatus.OK).body(personalDataLocationViewRepository);
	}

	@PostMapping
	public ResponseEntity<?> register(@RequestBody PersonalDataLocation personalDataLocation) {
		try {
			personalDataLocationService.register(personalDataLocation);
			return ResponseEntity.status(HttpStatus.CREATED).body(personalDataLocation);
		
		} catch (BusinessRuleException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			personalDataLocationService.delete(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		
		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
}
