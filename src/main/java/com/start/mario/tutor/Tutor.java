package com.start.mario.tutor;

import com.start.mario.plan.Plan;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tutor")
public class Tutor {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nombre;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "plan_id",referencedColumnName = "id")
	private Plan id_plan;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Plan getPlan() {
		return id_plan;
	}

	public void setPlan(Plan plan) {
		this.id_plan = plan;
	}

	@Override
	public String toString() {
		return nombre;
	}
}
