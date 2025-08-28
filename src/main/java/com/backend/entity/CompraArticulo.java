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
@Table(name = "compra_articulo")
@Data
public class CompraArticulo {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "El articulo es obligatorio")
	private Integer articulo;
	
	@NotNull(message = "La cantidad es obligatoria")
	private Integer cantidad;
	
	@NotNull(message = "La compra es obligatoria")
	private Integer compra;
	
	@NotNull(message = "El valor unitario es obligatorio")
	private Integer valor_unitario;
	
	@NotNull(message = "El valor es obligatorio")
	private Integer valor;
	
}
