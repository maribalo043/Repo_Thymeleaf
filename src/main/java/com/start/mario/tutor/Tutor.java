package com.start.mario.tutor;

import com.start.mario.plan.Plan;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Tutor {

	@Id
	private Long id;
	
	private String nombre;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="plan_id", referencedColumnName = "id")
	private Plan idPlan;

	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Plan getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(Plan idPlan) {
		this.idPlan = idPlan;
	}

	@Override
	public String toString() {
		return "Tutor [id=" + id + ", nombre=" + nombre + ", idPlan=" + idPlan.getNombre() + "]";
	}
	
	
	
	
}
