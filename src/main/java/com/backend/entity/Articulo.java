package com.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name = "articulo")
@Data
public class Articulo {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "El nombre es obligatorio")
	private String nombre;

	@NotNull(message = "La descripcion es obligatoria")
	private String descripcion;

	@NotNull(message = "La categoria es obligatoria")
	@JoinColumn(name = "categoria", nullable = false)
	private Categoria categoria;

	@NotNull(message = "La cantidad es obligatoria")
	private Integer cantidad;

	@NotNull(message = "El estado es obligatorio")
	private Integer estado;
}