package com.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "articulo_caracteristicas")
@Data
public class ArticuloCaracteristicas {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "El articulo es obligatorio")
	@ManyToOne
	@JoinColumn(name = "articulo", nullable = false)
	private Articulo articulo;

	@NotNull(message = "La caracteristica es obligatoria")
	@ManyToOne
	@JoinColumn(name = "caracteristica", nullable = false)
	private Caracteristica caracteristica;

	@NotBlank(message = "El valor es obligatorio")
	private String valor;

}
