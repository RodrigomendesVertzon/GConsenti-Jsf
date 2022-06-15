package br.com.vertzon.gconsenti.domain.service.correlation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vertzon.gconsenti.domain.model.correlation.CDataEntity;
import br.com.vertzon.gconsenti.domain.model.correlation.CorrelationFactor;
import br.com.vertzon.gconsenti.domain.model.correlation.NDataEntity;
import br.com.vertzon.gconsenti.domain.model.correlation.RDataEntity;
import br.com.vertzon.gconsenti.domain.repository.correlation.CorrelationFactorRepository;

@Service
public class CorrelationFactorService {

	@Autowired
	private CorrelationFactorRepository correlationFactorRepository;

	public CorrelationFactor registerCorrelationFactor(CDataEntity cDataEntity) {
		CorrelationFactor correlationFactor = correlationFactorRepository.findBycDataEntity(cDataEntity);
		if (correlationFactor == null) {
			correlationFactor = new CorrelationFactor();
			correlationFactor.setCDataEntity(cDataEntity);
			return correlationFactorRepository.save(correlationFactor);
		} else {
			return correlationFactor;
		}
	}

	public Optional<CorrelationFactor> findCorrelationFactor(CDataEntity cDataEntity) {
		Long cFactorId = correlationFactorRepository.findFactorByC(cDataEntity.getAid(), cDataEntity.getBid(),
				cDataEntity.getCid());
		if (cFactorId != null) {
			return correlationFactorRepository.findById(cFactorId);
		}
		return Optional.empty();
	}

	public Optional<CorrelationFactor> findCorrelationFactor(CDataEntity cDataEntity, RDataEntity rDataEntity) {
		Long cFactorId = correlationFactorRepository.findFactorByC(cDataEntity.getAid(), cDataEntity.getBid(),
				cDataEntity.getCid());

		if (rDataEntity != null && cFactorId != null) {
			correlationFactorRepository.updateFactor(rDataEntity, cFactorId);
			return correlationFactorRepository.findById(cFactorId);
		}
		return Optional.empty();
	}

	public Optional<CorrelationFactor> findCorrelationFactor(CDataEntity cDataEntity, NDataEntity nDataEntity) {
		Long cFactorId = correlationFactorRepository.findFactorByC(cDataEntity.getAid(), cDataEntity.getBid(),
				cDataEntity.getCid());

		if (nDataEntity != null && cFactorId != null) {
			correlationFactorRepository.updateFactor(nDataEntity, cFactorId);
			return correlationFactorRepository.findById(cFactorId);
		}
		
		return Optional.empty();
	}
}
