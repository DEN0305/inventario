package com.backend.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name = "venta")
@Data
public class Venta {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "La fecha es obligatoria")
	private LocalDate fecha; 

	private Integer cliente;

	@NotNull(message = "El valor es obligatorio")
	private Integer valor;
}