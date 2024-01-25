package com.start.mario.Enmarca;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class EnmarcaKey implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column
	private Long idPlan;
	
	@Column 
	private Long idActividad;

	public Long getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(Long idPlan) {
		this.idPlan = idPlan;
	}

	public Long getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(Long idActividad) {
		this.idActividad = idActividad;
	}
	@Override
	public int hashCode() {
		return Objects.hash(idActividad, idPlan);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnmarcaKey other = (EnmarcaKey) obj;
		return Objects.equals(idActividad, other.idActividad) && Objects.equals(idPlan, other.idPlan);
	}
}
