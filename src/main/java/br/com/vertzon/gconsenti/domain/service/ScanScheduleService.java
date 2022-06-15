package br.com.vertzon.gconsenti.domain.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vertzon.gconsenti.domain.exception.BusinessRuleException;
import br.com.vertzon.gconsenti.domain.exception.EntityInUseException;
import br.com.vertzon.gconsenti.domain.exception.EntityNotFoundException;
import br.com.vertzon.gconsenti.domain.model.ScanSchedule;
import br.com.vertzon.gconsenti.domain.model.enumerator.ScanScheduleStatus;
import br.com.vertzon.gconsenti.domain.repository.ErrorMessageRepository;
import br.com.vertzon.gconsenti.domain.repository.ScanScheduleRepository;

@Service
public class ScanScheduleService {
	
	@Autowired
	private ScanScheduleRepository scanScheduleRepository;
	@Autowired
	private ErrorMessageRepository errorMessagesRepository;

	public Optional<ScanSchedule> getLastPerDatasourceAndDriver(String name, String driver, LocalDateTime startDate) {
		return scanScheduleRepository.findTop1ByDatasourceNameAndDatasourceDatabaseTypeDriverAndStatusAndDatetimeBetweenOrderByIdDesc(name, driver,
				ScanScheduleStatus.AGUARDANDO, startDate, startDate.plus(5, ChronoUnit.MINUTES));
	}

	public Optional<ScanSchedule> getLastPerFilesource(String name, LocalDateTime startDate, LocalDateTime delayTime) {
		return scanScheduleRepository.findTop1ByFilesourceNameAndStatusAndDatetimeBetweenOrderByIdDesc(name,
				ScanScheduleStatus.AGUARDANDO, startDate, delayTime);
	}

	@Transactional
	public ScanSchedule register(ScanSchedule scanSchedule) {
		if (scanSchedule.getDatetime() == null) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0138"));
		}
		if (scanSchedule.getDatetime().isBefore(LocalDateTime.now().minus(59, ChronoUnit.SECONDS))) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0139"));
		}
		if ((scanSchedule.getDatasource() == null && scanSchedule.getFilesource() == null)) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0140"));
		}
		if (scanSchedule.getType() == null) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0141"));
		}
//		if (scanSchedule.getDatasource() != null) {
//			if (this.getLastDatasourceSchedule(scanSchedule.getDatasource().getName(),
//					scanSchedule.getDatasource().getDatabaseType().getDriver(),
//					scanSchedule.getDatetime().plus(5, ChronoUnit.MINUTES)).isPresent() && !scanSchedule.isEditing()) {
//				throw new BusinessRuleException(
//						"Não é possível marcar duas varreduras de tipo \"ÚNICO\" para a mesma fonte de dados sem que a anterior seja completada.");
//			}
//		}
		scanSchedule.setStatus(ScanScheduleStatus.AGUARDANDO);
		return scanScheduleRepository.saveAndFlush(scanSchedule);
	}

	@Transactional
	public ScanSchedule update(ScanSchedule scanSchedule) {
		try {
			return scanScheduleRepository.saveAndFlush(scanSchedule);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0142")
							, scanSchedule.getId()));
		}
	}

	public void updateStatus(ScanSchedule scanSchedule, ScanScheduleStatus scanScheduleStatus) {
		scanSchedule.setStatus(scanScheduleStatus);
		update(scanSchedule);
	}

	@Transactional
	public void delete(Long id) {
		try {
			scanScheduleRepository.deleteById(id);
			scanScheduleRepository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0100"), id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0101"), id));
		}
	}

	@Transactional(readOnly = true)
	public List<ScanSchedule> search(ScanSchedule scanScheduleFilter) {
		Example<ScanSchedule> example = Example.of(scanScheduleFilter,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return scanScheduleRepository.findAll(example);
	}
}
