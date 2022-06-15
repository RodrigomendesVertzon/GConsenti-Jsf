package br.com.vertzon.gconsenti.controller.api.v1;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.vertzon.gconsenti.domain.exception.BusinessRuleException;
import br.com.vertzon.gconsenti.domain.exception.EntityInUseException;
import br.com.vertzon.gconsenti.domain.exception.EntityNotFoundException;
import br.com.vertzon.gconsenti.domain.model.ScanSchedule;
import br.com.vertzon.gconsenti.domain.model.enumerator.ScanScheduleStatus;
import br.com.vertzon.gconsenti.domain.repository.ScanScheduleRepository;
import br.com.vertzon.gconsenti.domain.service.ScanScheduleService;

@RestController
@RequestMapping("/api/v1/scanschedules")
public class ScanScheduleController {

	@Autowired
	private ScanScheduleRepository scanScheduleRepository;
	@Autowired
	private ScanScheduleService scanScheduleService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<ScanSchedule> scanSchedules = scanScheduleRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(scanSchedules);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable(value = "id", required = true) Long id) {
		Optional<ScanSchedule> scanSchedule = scanScheduleRepository.findById(id);
		if (scanSchedule.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(scanSchedule);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@GetMapping("/datasources/last")
	public ResponseEntity<?> getLastScanSchedule(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "driver", required = true) String driver,
			@RequestParam(value = "startDate", required = true) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime startDate) {
		Optional<ScanSchedule> scanSchedule = scanScheduleService
				.getLastPerDatasourceAndDriver(name, driver, startDate);
		if (scanSchedule.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(scanSchedule);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@GetMapping("/filesources/last")
	public ResponseEntity<?> getLastScanScheduleByFilesource(@RequestParam(value = "name") String name,
			@RequestParam(value = "startDate", required = true) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime startDate) {
		Optional<ScanSchedule> scanSchedule = scanScheduleService.getLastPerFilesource(name, startDate,
				startDate.plus(5, ChronoUnit.MINUTES));
		if (scanSchedule.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(scanSchedule);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PostMapping
	public ResponseEntity<?> register(@RequestBody ScanSchedule scanSchedule) {
		try {
			scanScheduleService.register(scanSchedule);
			return ResponseEntity.status(HttpStatus.CREATED).body(scanSchedule);
		} catch (BusinessRuleException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PatchMapping("/status/{id}")
	public ResponseEntity<?> updateStatus(@PathVariable("id") Long id, @RequestBody ScanSchedule dto) {
		return scanScheduleRepository.findById(id).map(entity -> {
			ScanScheduleStatus updatedStatus = dto.getStatus();

			if (updatedStatus != ScanScheduleStatus.AGUARDANDO && updatedStatus != ScanScheduleStatus.EXECUTANDO
					&& updatedStatus != ScanScheduleStatus.SUCESSO && updatedStatus != ScanScheduleStatus.FALHA) {
				return ResponseEntity.badRequest()
						.body("Não foi possível atualizar o status do agendamento. Envie um status válido.");
			}
			try {
				entity.setStatus(updatedStatus);
				scanScheduleService.update(entity);
				return ResponseEntity.ok(entity);
			} catch (BusinessRuleException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}).orElseGet(() -> new ResponseEntity<String>("Agendamento não encontrado", HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			scanScheduleService.delete(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
}
