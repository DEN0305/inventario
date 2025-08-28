package com.backend.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

	@NotNull(message = "El valor es obligatorio")
	private Integer valor;
	
    @ManyToOne
    @JoinColumn(name = "cliente_id") 
    private Cliente cliente;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<VentaArticulo> ventaArticulos;
}