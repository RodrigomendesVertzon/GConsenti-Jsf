package br.com.vertzon.gconsenti.controller.api.v1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vertzon.gconsenti.domain.model.correlation.CDataEntity;
import br.com.vertzon.gconsenti.domain.model.correlation.CorrelationDTO;
import br.com.vertzon.gconsenti.domain.model.correlation.CorrelationFactor;
import br.com.vertzon.gconsenti.domain.model.correlation.DataEntity;
import br.com.vertzon.gconsenti.domain.model.correlation.NDataEntity;
import br.com.vertzon.gconsenti.domain.model.correlation.OtherCorrelationDTO;
import br.com.vertzon.gconsenti.domain.model.correlation.RDataEntity;
import br.com.vertzon.gconsenti.domain.repository.correlation.CorrelationFactorRepository;
import br.com.vertzon.gconsenti.domain.service.correlation.CDataEntityService;
import br.com.vertzon.gconsenti.domain.service.correlation.CorrelationEntityService;
import br.com.vertzon.gconsenti.domain.service.correlation.CorrelationFactorService;
import br.com.vertzon.gconsenti.domain.service.correlation.NDataEntityService;
import br.com.vertzon.gconsenti.domain.service.correlation.RDataEntityService;
import br.com.vertzon.gconsenti.util.DataEntityUtil;

@RestController
@RequestMapping("/api/v1/correlations")
public class CorrelationController {

	@Autowired
	private CDataEntityService cDataEntityService;
	@Autowired
	private RDataEntityService rDataEntityService;
	@Autowired
	private NDataEntityService nDataEntityService;
	
	@Autowired
	private CorrelationFactorRepository correlationFactorRepository;
	@Autowired
	private CorrelationFactorService correlationFactorService;
	
	@Autowired
	private CorrelationEntityService correlationEntityService;
	
	@GetMapping("/c")
	public ResponseEntity<Long> getFactorByC(@Param("aid") String aid, @Param("bid") String bid, @Param("cid") String cid) {
		Long cFactorId = correlationFactorRepository.findFactorByC(aid, bid, cid);
		if (cFactorId != null) {
			return ResponseEntity.status(HttpStatus.OK).body(cFactorId);
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping("/column")
	public ResponseEntity<?> getColumnFromDataEntity(@Param("tableName") String tableName,
			@Param("datasourceName") String datasourceName) {
		if (tableName != null && datasourceName != null) {
			String column = correlationFactorRepository.findCDataEntityByTableAndDatasource(tableName,
					datasourceName);
			return ResponseEntity.status(HttpStatus.OK).body(column);
		} else {
			return ResponseEntity.status(HttpStatus.OK).build();
		}
	}
	
	@PostMapping("/c")
	public void registerCorrelationForCDataEntities(@RequestBody List<CorrelationDTO> dto) {
		for (CorrelationDTO correlationDTO : dto) {

			CDataEntity cDataEntity = new CDataEntity();
			DataEntity dataEntity = DataEntityUtil.shuffle(correlationDTO.getcValue());
			cDataEntity.setAid(dataEntity.getAid());
			cDataEntity.setBid(dataEntity.getBid());
			cDataEntity.setCid(dataEntity.getCid());
			
			cDataEntity = cDataEntityService.register(cDataEntity);
			
			CorrelationFactor correlationFactor = correlationFactorService.registerCorrelationFactor(cDataEntity);
			
			correlationEntityService.correlateFactors(correlationFactor, correlationDTO);
		}
	}	
	
	@PostMapping("/r")
	public void registerCorrelationForRDataEntities(@RequestBody List<CorrelationDTO> dto) {
		for (CorrelationDTO correlationDTO : dto) {
			RDataEntity rDataEntity = new RDataEntity();
			DataEntity dataEntity = DataEntityUtil.shuffle(correlationDTO.getrValue());
			rDataEntity.setAid(dataEntity.getAid());
			rDataEntity.setBid(dataEntity.getBid());
			rDataEntity.setCid(dataEntity.getCid());
			
			CDataEntity cDataEntity = new CDataEntity();
			dataEntity = DataEntityUtil.shuffle(correlationDTO.getcValue());
			cDataEntity.setAid(dataEntity.getAid());
			cDataEntity.setBid(dataEntity.getBid());
			cDataEntity.setCid(dataEntity.getCid());
						
			rDataEntity = rDataEntityService.register(rDataEntity);

			Optional<CorrelationFactor> correlationFactor = correlationFactorService.findCorrelationFactor(cDataEntity, rDataEntity);
			
			if(correlationFactor.isPresent()) {
				correlationEntityService.correlateFactors(correlationFactor.get(), correlationDTO);
			}
		}
	}
	
	@PostMapping("/n")
	public void registerCorrelationForNDataEntities(@RequestBody List<CorrelationDTO> dto) {
		for (CorrelationDTO correlationDTO : dto) {
			NDataEntity nDataEntity = new NDataEntity();
			DataEntity dataEntity = DataEntityUtil.shuffle(correlationDTO.getnValue());
			nDataEntity.setAid(dataEntity.getAid());
			nDataEntity.setBid(dataEntity.getBid());
			nDataEntity.setCid(dataEntity.getCid());
			
			CDataEntity cDataEntity = new CDataEntity();
			dataEntity = DataEntityUtil.shuffle(correlationDTO.getcValue());
			cDataEntity.setAid(dataEntity.getAid());
			cDataEntity.setBid(dataEntity.getBid());
			cDataEntity.setCid(dataEntity.getCid());
			
			nDataEntity = nDataEntityService.register(nDataEntity);

			Optional<CorrelationFactor> correlationFactor = correlationFactorService.findCorrelationFactor(cDataEntity, nDataEntity);

			if(correlationFactor.isPresent()) {
				correlationEntityService.correlateFactors(correlationFactor.get(), correlationDTO);
			}		
		}
	}
	
	@PostMapping("/o")
	public void registerCorrelationForODataEntities(@RequestBody List<OtherCorrelationDTO> dto) {
		for (OtherCorrelationDTO otherCorrelationDTO : dto) {
			
			DataEntity dataEntity = DataEntityUtil.shuffle(otherCorrelationDTO.getOValue());
			CDataEntity cDataEntity = new CDataEntity();
			cDataEntity.setAid(dataEntity.getAid());
			cDataEntity.setBid(dataEntity.getBid());
			cDataEntity.setCid(dataEntity.getCid());
						
			Optional<CorrelationFactor> correlationFactor = correlationFactorService.findCorrelationFactor(cDataEntity);
			
			if(correlationFactor.isPresent()) {
				correlationEntityService.correlateFactors(correlationFactor.get(), Long.valueOf(otherCorrelationDTO.getPersonalDataLocationId()));
			}
		}
	}
}
