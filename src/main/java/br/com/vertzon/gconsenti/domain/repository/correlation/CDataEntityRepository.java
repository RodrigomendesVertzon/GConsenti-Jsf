package br.com.vertzon.gconsenti.domain.repository.correlation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.vertzon.gconsenti.domain.model.correlation.CDataEntity;

@Repository
public interface CDataEntityRepository extends JpaRepository<CDataEntity, Long> {
	
	@Query(value = "from CDataEntity")
	public List<Optional<CDataEntity>> wsFindAll();
	
	public boolean existsByAidAndBidAndCid(String aid, String bid, String cid);
	
	public CDataEntity findByAidAndBidAndCid(String aid, String bid, String cid);
	
}
