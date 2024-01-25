package com.start.mario.Enmarca;

import java.util.Set;

import com.start.mario.actividad.Actividad;
import com.start.mario.plan.Plan;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "enmarca")
public class Enmarca {
	
	@EmbeddedId EnmarcaKey id;
	
	@ManyToOne
	@MapsId("idPlan")
	@JoinColumn
	Plan plan;
	
	@ManyToOne
	@MapsId("idActividad")
	@JoinColumn
	Actividad actividad;
	
	String fecha;
	

}
