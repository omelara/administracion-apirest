package com.empresa.administracion.apirest.models.entities;

import java.io.Serializable;



import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "alquileres", schema="public", catalog="db_administracion")
public class Alquiler implements Serializable {
private static final long serialVersionUID = 1L;
	
@Id
@Column(name="id", nullable=false)
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;

@Column(name="fecha_despacho",nullable=true)
private Date fechaDespacho;

@Column(name="fecha",nullable=false)
private Date fecha;

@Column(name="cantidad",nullable=true,length=200)
private Double cantidad;

@Column(name="estado",nullable=false, precision=2)
private String estado;


@ManyToOne(fetch = FetchType.LAZY)
@JsonIgnoreProperties({"hibernateLazyInitializer","hanler"})
@JoinColumn(name = "empleado_id", referencedColumnName = "id", nullable = false )
private Empleado empleado;

//Relacion uno a muchos con detalle
@OneToMany(mappedBy = "alquiler", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<DetalleAlquiler> detalleAlquiler;

@PrePersist
private void setEstado() {
	this.estado = "R";
	}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Date getFecha() {
	return fecha;
}

public void setFecha(Date fecha) {
	this.fecha = fecha;
}

public Double getCantidad() {
	return cantidad;
}

public void setCantidad(Double cantidad) {
	this.cantidad = cantidad;
}

public String getEstado() {
	return estado;
}

public void setEstado(String estado) {
	this.estado = estado;
}

public Empleado getEmpleado() {
	return empleado;
}

public void setEmpleado(Empleado empleado) {
	this.empleado = empleado;
}

public Date getFechaDespacho() {
	return fechaDespacho;
}

public void setFechaDespacho(Date fechaDespacho) {
	this.fechaDespacho = fechaDespacho;
}

public List<DetalleAlquiler> getDetalleAlquiler() {
	return detalleAlquiler;
}

public void setDetalleAlquiler(List<DetalleAlquiler> detalleAlquiler) {
	this.detalleAlquiler = detalleAlquiler;
}

public Object getNombre() {
	// TODO Auto-generated method stub
	return null;
}

public void setNombre(Object nombre) {
	// TODO Auto-generated method stub
	
}



} 