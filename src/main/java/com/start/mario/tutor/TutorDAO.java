package com.start.mario.tutor;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TutorDAO extends CrudRepository<Tutor,Long>{
	
	@Query(value="SELECT * FROM tutor where PLAN_ID is null", nativeQuery = true)
	List<Tutor> getTutoresNoEnlazados();
	
	@Query(value="SELECT * FROM tutor where PLAN_ID = :plan", nativeQuery = true)
	Optional<Tutor> getTutorPorPlan(Long plan);
}
