package com.empresa.administracion.apirest.models.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="empleados", schema="public", catalog="db_administracion")
public class Empleado implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id", nullable=false, length=100)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nombre",nullable=false,length=100)
	private String nombre;
	
	@Column(name="apellido",nullable=false,length=100)
	private String apellido;
	
	@Column(name="telefono",nullable=true, length=15)
	private String telefono;
	
	@Column(name="correo",nullable=false, length=80, unique=true)
	private String correo;
	
	@Column(name="direccion", nullable=false, length=200)
	private String direccion;
	
	@Column(name="salario",nullable=true, length=15)
	private double salario;
	
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	@Column(name="estado",nullable=false, length=1)
	private String estado;
	
	//Creando la relacion uno a muchos con Prestamo
	@OneToMany(mappedBy = "empleado", cascade = CascadeType.REFRESH, orphanRemoval = true)
	@JsonBackReference
	private List<Alquiler> prestmos = new ArrayList<>();
	
	@PrePersist
	public void setCreateAt() {
		this.createAt = new Date();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return nombre;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
}
