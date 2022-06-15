package br.com.vertzon.gconsenti.domain.repository.correlation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.vertzon.gconsenti.domain.model.correlation.RDataEntity;

@Repository
public interface RDataEntityRepository extends JpaRepository<RDataEntity, Long> {
	
	@Query(value = "FROM RDataEntity")
	public List<Optional<RDataEntity>> wsFindAll();
	
	public boolean existsByAidAndBidAndCid(String aid, String bid, String cid);

	public RDataEntity findByAidAndBidAndCid(String aid, String bid, String cid);
	
}
