package com.sena.libreria.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="multa")
public class multa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id_multa", nullable=false, length=36)
    private String id_multa;
    
    @ManyToOne
	@JoinColumn(name="id_prestamo")
	private prestamo prestamo;

    @Column(name="usua_multado", nullable=false, length=100)
    private String usua_multado;

    @Column(name="valor_multa", nullable=false, length=100)
    private String valor_multa;

    @Column(name="fecha_multa", nullable=false, length=25)
    private String fecha_multa;

    @Column(name="estado_multa", nullable=false, length=10)
    private String estado_multa;

	public multa() {
		super();
	}

	public multa(String id_multa, String usua_multado, String prestamo, String valor_multa, String fecha_multa,
			String estado_multa) {
		super();
		this.id_multa = id_multa;
		this.usua_multado = usua_multado;
		this.valor_multa = valor_multa;
		this.fecha_multa = fecha_multa;
		this.estado_multa = estado_multa;
	}

	public String getId_multa() {
		return id_multa;
	}

	public void setId_multa(String id_multa) {
		this.id_multa = id_multa;
	}

	public String getUsua_multado() {
		return usua_multado;
	}

	public void setUsua_multado(String usua_multado) {
		this.usua_multado = usua_multado;
	}

	public String getValor_multa() {
		return valor_multa;
	}

	public void setValor_multa(String valor_multa) {
		this.valor_multa = valor_multa;
	}

	public String getFecha_multa() {
		return fecha_multa;
	}

	public void setFecha_multa(String fecha_multa) {
		this.fecha_multa = fecha_multa;
	}

	public String getEstado_multa() {
		return estado_multa;
	}

	public void setEstado_multa(String estado_multa) {
		this.estado_multa = estado_multa;
	}

}
