package br.com.vertzon.gconsenti.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vertzon.gconsenti.domain.model.Filesource;

@Repository
public interface FilesourceRepository extends JpaRepository<Filesource, Long>  {

}
