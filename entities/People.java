package com.ibero.web.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "peoples")
public class People implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty (message = "¡El nombres no debe ser vacío!")
	@NotBlank (message = "Complete el nombres con valores")
	@Column   (name = "names")
	private String names;
	
	@NotEmpty(message = "¡El apellidos no debe ser vacío!")
	@NotBlank (message = "Complete el apellidos con valores")
	private String lastNames;
	
	private String FullNames;
	
	//Cuando se crea una nuevo registro y se actualiza:
	@PrePersist
	@PreUpdate
	public void Prepersist() {
		this.FullNames = this.names + " " + this.lastNames;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getLastNames() {
		return lastNames;
	}

	public void setLastNames(String lastNames) {
		this.lastNames = lastNames;
	}

	public String getFullNames() {
		return FullNames;
	}

	public void setFullNames(String fullNames) {
		FullNames = fullNames;
	}
	private static final long serialVersionUID = 1L;
}
