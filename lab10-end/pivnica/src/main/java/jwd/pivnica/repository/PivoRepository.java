package jwd.pivnica.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.pivnica.model.Pivo;


@Repository
public interface PivoRepository extends JpaRepository<Pivo, Long>  {
	Page<Pivo> findByPivaraId(Long pivaraId, Pageable pageRequest);
	
	@Query(
		"SELECT p FROM Pivo p WHERE"
		+ "(:naziv IS NULL OR p.naziv LIKE :naziv) AND"
		+ "(:minibu IS NULL OR p.ibu >= :minibu) AND"
		+ "(:maxibu IS NULL OR p.ibu <= :maxibu) AND"
		+ "(:pivaraId IS NULL OR p.pivara.id = :pivaraId)"
	)
	Page<Pivo> pretraga(
			@Param("naziv") String naziv,
			@Param("minibu") Float minibu,
			@Param("maxibu") Float maxibu,
			@Param("pivaraId") Long pivaraId,
			Pageable pageable);
}
