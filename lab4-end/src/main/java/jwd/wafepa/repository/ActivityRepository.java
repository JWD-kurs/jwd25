package jwd.wafepa.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.Activity;


@Repository
@Transactional
public interface ActivityRepository 
	extends JpaRepository<Activity, Long> {
	
	//JPQL
	//@Query("SELECT a FROM Activity a WHERE a.name = :activityName")
	//List<Activity> findByName(@Param("activityName") String name);
	

	List<Activity> findByName(String name);
}
