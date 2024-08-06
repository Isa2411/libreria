package com.sena.libreria.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="prestamo")
public class prestamo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name="id_prestamo")
	private String id_prestamo;
	
	@Column(name="fecha_prestamo")
	private String fecha_prestamo;

	@Column(name="fecha_devo")
	private String fecha_devo;

	@Column(name="usua_prestamo")
	private String usua_prestamo;

	@Column(name="libro_prestado")
	private String libro_prestado;
	
	@Column(name="estado_prestamo")
	private String estado_prestamo;

	public prestamo() {
		super();
	}

	public prestamo(String id_prestamo, String fecha_prestamo, String fecha_devo, String usua_prestamo,
			String libro_prestado, String estado_prestamo) {
		super();
		this.id_prestamo = id_prestamo;
		this.fecha_prestamo = fecha_prestamo;
		this.fecha_devo = fecha_devo;
		this.usua_prestamo = usua_prestamo;
		this.libro_prestado = libro_prestado;
		this.estado_prestamo = estado_prestamo;
	}

	public String getId_prestamo() {
		return id_prestamo;
	}

	public void setId_prestamo(String id_prestamo) {
		this.id_prestamo = id_prestamo;
	}

	public String getFecha_prestamo() {
		return fecha_prestamo;
	}

	public void setFecha_prestamo(String fecha_prestamo) {
		this.fecha_prestamo = fecha_prestamo;
	}

	public String getFecha_devo() {
		return fecha_devo;
	}

	public void setFecha_devo(String fecha_devo) {
		this.fecha_devo = fecha_devo;
	}

	public String getUsua_prestamo() {
		return usua_prestamo;
	}

	public void setUsua_prestamo(String usua_prestamo) {
		this.usua_prestamo = usua_prestamo;
	}

	public String getLibro_prestado() {
		return libro_prestado;
	}

	public void setLibro_prestado(String libro_prestado) {
		this.libro_prestado = libro_prestado;
	}

	public String getEstado_prestamo() {
		return estado_prestamo;
	}

	public void setEstado_prestamo(String estado_prestamo) {
		this.estado_prestamo = estado_prestamo;
	}

	

}
