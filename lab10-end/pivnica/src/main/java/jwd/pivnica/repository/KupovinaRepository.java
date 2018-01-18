package jwd.pivnica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.pivnica.model.Kupovina;

@Repository
public interface KupovinaRepository extends JpaRepository<Kupovina, Long>{
	
}
