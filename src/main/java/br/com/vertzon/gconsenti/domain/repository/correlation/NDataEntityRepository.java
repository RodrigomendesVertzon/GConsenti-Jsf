package br.com.vertzon.gconsenti.domain.repository.correlation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.vertzon.gconsenti.domain.model.correlation.NDataEntity;

@Repository
public interface NDataEntityRepository extends JpaRepository<NDataEntity, Long> {
	
	@Query(value = "FROM NDataEntity")
	public List<Optional<NDataEntity>> wsFindAll();
	
	public boolean existsByAidAndBidAndCid(String aid, String bid, String cid);
	
	public NDataEntity findByAidAndBidAndCid(String aid, String bid, String cid);
	
}
