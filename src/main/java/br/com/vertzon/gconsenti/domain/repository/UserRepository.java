package br.com.vertzon.gconsenti.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vertzon.gconsenti.domain.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	public Optional<User> findByEmail(String email);
	
	public boolean existsByEmail(String email);
	
}
