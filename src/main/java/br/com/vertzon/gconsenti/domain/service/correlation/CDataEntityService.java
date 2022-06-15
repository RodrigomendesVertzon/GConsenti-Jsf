package br.com.vertzon.gconsenti.domain.service.correlation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vertzon.gconsenti.domain.model.correlation.CDataEntity;
import br.com.vertzon.gconsenti.domain.repository.correlation.CDataEntityRepository;

@Service
public class CDataEntityService {

	@Autowired
	private CDataEntityRepository cDataEntityRepository;

	@Transactional
	public CDataEntity register(CDataEntity cDataEntity) {
		if (!cDataEntityRepository.existsByAidAndBidAndCid(cDataEntity.getAid(), cDataEntity.getBid(),
				cDataEntity.getCid())) {
			return cDataEntityRepository.saveAndFlush(cDataEntity);
		} else {
			return cDataEntityRepository.findByAidAndBidAndCid(cDataEntity.getAid(), cDataEntity.getBid(), cDataEntity.getCid());
		}
	}
}
