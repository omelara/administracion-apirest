  package com.empresa.administracion.apirest.models.entities;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="detalle_alquileres", schema = "public", catalog = "db_administracion")
public class DetalleAlquiler implements Serializable  {
private static final long serialVersionUID = 1L;
	

@Id
@Column(name = "id", nullable = false)
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;

@Column(name = "cantidad", nullable = false)
private Integer cantidad;

@ManyToOne
@JoinColumn(name = "maquina_id", referencedColumnName = "id", nullable = false)
private Maquina maquina;

@ManyToOne
@JsonBackReference
@JoinColumn(name = "alquiler_id", referencedColumnName = "id", nullable = false)
private Alquiler alquiler;
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		
		public Integer getCantidad() {
			return cantidad;
		}
		public void setCantidad(Integer cantidad) {
			this.cantidad = cantidad;
		}
		
		public Maquina getMaquina() {
			return maquina;
		}
		public void setMaquina(Maquina maquina) {
			this.maquina = maquina;
		}
		
		public Alquiler getAlquiler() {
			return alquiler;
		}
		public void setAlquiler(Alquiler alquiler) {
			this.alquiler = alquiler;
		}
			
			
}
