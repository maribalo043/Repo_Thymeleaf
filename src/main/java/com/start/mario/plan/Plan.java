package com.start.mario.plan;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.start.mario.Enmarca.Enmarca;
import com.start.mario.actividad.Actividad;
import com.start.mario.curso.Curso;
import com.start.mario.tutor.Tutor;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "plan")
public class Plan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;	
	
	private String nombre;
	@JoinColumn(name = "FK_CURSO")
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	private Curso idCurso;
	
	@OneToOne(mappedBy="id_plan")
	private Tutor tutor;
	
	@OneToMany(
			targetEntity=Enmarca.class,
			mappedBy="plan")
	private Set<Actividad> actividad = new HashSet<Actividad>();
	
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
	public Curso getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(Curso idCurso) {
		this.idCurso = idCurso;
	}
	public Tutor getTutor() {
		return tutor;
	}
	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}
	@Override
	public String toString() {
		return "Plan [id=" + id + ", nombre=" + nombre + ", idCurso=" + idCurso + ", tutor=" + tutor + "]";
	}
	
}
