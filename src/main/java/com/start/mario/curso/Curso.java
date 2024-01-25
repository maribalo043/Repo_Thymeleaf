package com.start.mario.curso;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.start.mario.plan.Plan;

import jakarta.persistence.*;

@Entity
@Table(name = "curso")
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nombre;
	
	@OneToMany(mappedBy="idCurso",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Plan> planes = new ArrayList<Plan>();

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
	@Override
	public String toString() {
		return nombre;
	}
	public List<Plan> getPlanes() {
		return planes;
	}
	public void setPlanes(List<Plan> planes) {
		this.planes = planes;
	}
	
}
