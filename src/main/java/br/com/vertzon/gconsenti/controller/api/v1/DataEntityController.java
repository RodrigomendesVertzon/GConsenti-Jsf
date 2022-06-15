package br.com.vertzon.gconsenti.controller.api.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vertzon.gconsenti.domain.model.correlation.CDataEntity;
import br.com.vertzon.gconsenti.domain.model.correlation.NDataEntity;
import br.com.vertzon.gconsenti.domain.model.correlation.RDataEntity;
import br.com.vertzon.gconsenti.domain.repository.correlation.CDataEntityRepository;
import br.com.vertzon.gconsenti.domain.repository.correlation.NDataEntityRepository;
import br.com.vertzon.gconsenti.domain.repository.correlation.RDataEntityRepository;

@RestController
@RequestMapping("/api/v1/dataentities")
public class DataEntityController {

	@Autowired
	private CDataEntityRepository cDataEntityRepository;
	@Autowired
	private RDataEntityRepository rDataEntityRepository;
	@Autowired
	private NDataEntityRepository nDataEntityRepository;

	@GetMapping("/c")
	public ResponseEntity<?> getAllCDataEntities() {
		List<CDataEntity> cDataEntities = cDataEntityRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(cDataEntities);
	}
	
	@GetMapping("/r")
	public ResponseEntity<?> getAllRDataEntities() {
		List<RDataEntity> rDataEntities = rDataEntityRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(rDataEntities);
	}
	
	@GetMapping("/n")
	public ResponseEntity<?> getAllNDataEntities() {
		List<NDataEntity> nDataEntities = nDataEntityRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(nDataEntities);
	}

}
