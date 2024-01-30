package com.start.mario.enmarca;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface EnmarcaDAO extends CrudRepository<Enmarca, Long>{
	
	 @Query("SELECT COUNT(e) > 0 FROM Enmarca e WHERE e.plan.nombre = :nombrePlan AND e.actividad.nombre = :nombreActividad")
	    boolean existsByPlanAndActividad(@Param("nombrePlan") String nombrePlan,
	                                     @Param("nombreActividad") String nombreActividad);
}
