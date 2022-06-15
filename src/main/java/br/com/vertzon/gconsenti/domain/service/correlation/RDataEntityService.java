package br.com.vertzon.gconsenti.domain.service.correlation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vertzon.gconsenti.domain.model.correlation.RDataEntity;
import br.com.vertzon.gconsenti.domain.repository.correlation.RDataEntityRepository;

@Service
public class RDataEntityService {

	@Autowired
	private RDataEntityRepository rDataEntityRepository;

	@Transactional
	public RDataEntity register(RDataEntity rDataEntity) {
		if (!rDataEntityRepository.existsByAidAndBidAndCid(rDataEntity.getAid(), rDataEntity.getBid(),
				rDataEntity.getCid())) {
			return rDataEntityRepository.saveAndFlush(rDataEntity);
		} else {
			return rDataEntityRepository.findByAidAndBidAndCid(rDataEntity.getAid(), rDataEntity.getBid(), rDataEntity.getCid());
		}
	}
}
