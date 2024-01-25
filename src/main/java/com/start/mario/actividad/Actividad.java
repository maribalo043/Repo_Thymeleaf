package com.start.mario.actividad;

import java.util.HashSet;
import java.util.Set;

import com.start.mario.Enmarca.Enmarca;
import com.start.mario.plan.Plan;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "actividad")
public class Actividad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private boolean obligatoria;
	private String nombre;
	private String descripcion;
	
	@OneToMany(
			targetEntity = Enmarca.class,
			mappedBy = "actividad")
	private Set<Plan> plan = new HashSet<Plan>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean isObligatoria() {
		return obligatoria;
	}
	public void setObligatoria(boolean obligatoria) {
		this.obligatoria = obligatoria;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "Actividad [id=" + id + ", obligatoria=" + obligatoria + ", nombre=" + nombre + ", descripcion="
				+ descripcion + "]";
	}
}
