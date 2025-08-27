package com.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name = "proveedor")
@Data
public class Proveedor {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "El nombre es obligatorio")
	private String nombre;

	@NotNull(message = "La direccion es obligatoria")
	private String direccion;

	@NotNull(message = "El telefono es obligatorio")
	private String telefono;

	@NotNull(message = "El correo es obligatorio")
	private String correo;
}