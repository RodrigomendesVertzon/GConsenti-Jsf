package br.com.vertzon.gconsenti.domain.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vertzon.gconsenti.domain.model.ScanSchedule;
import br.com.vertzon.gconsenti.domain.model.enumerator.ScanScheduleStatus;

@Repository
public interface ScanScheduleRepository extends JpaRepository<ScanSchedule, Long> {
		
	public Optional<ScanSchedule> findTop1ByDatasourceNameAndDatasourceDatabaseTypeDriverAndStatusAndDatetimeBetweenOrderByIdDesc(
			String name, String driver, Enum<ScanScheduleStatus> status, LocalDateTime startDate, LocalDateTime delayTime);

	public Optional<ScanSchedule> findTop1ByFilesourceNameAndStatusAndDatetimeBetweenOrderByIdDesc(String name, Enum<ScanScheduleStatus> status,
			LocalDateTime startDate, LocalDateTime delayTime);
}
