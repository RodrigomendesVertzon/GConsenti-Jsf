package br.com.vertzon.gconsenti.domain.service.correlation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vertzon.gconsenti.domain.model.correlation.NDataEntity;
import br.com.vertzon.gconsenti.domain.repository.correlation.NDataEntityRepository;

@Service
public class NDataEntityService {

	@Autowired
	private NDataEntityRepository nDataEntityRepository;
	
	@Transactional
	public NDataEntity register(NDataEntity nDataEntity) {
		System.out.printf("%s%s%s", nDataEntity.getAid(), nDataEntity.getBid(), nDataEntity.getCid());
		if (!nDataEntityRepository.existsByAidAndBidAndCid(nDataEntity.getAid(), nDataEntity.getBid(),
				nDataEntity.getCid())) {
			return nDataEntityRepository.saveAndFlush(nDataEntity);
		} else {
			return nDataEntityRepository.findByAidAndBidAndCid(nDataEntity.getAid(), nDataEntity.getBid(), nDataEntity.getCid());
		}
	}

}
