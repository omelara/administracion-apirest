 package com.empresa.administracion.apirest.models.dao;

import java.util.Date;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.empresa.administracion.apirest.models.entities.Alquiler;

public interface IAlquilerDAO extends CrudRepository<Alquiler, Long> {
	
	@Query("FROM Alquiler o WHERE o.estado = 'R' ORDER BY o.fecha DESC")
	List<Alquiler> findAllRecibidos();
	
	@Query("FROM Alquiler o WHERE o.estado = 'D' ORDER BY o.fecha DESC")
	List<Alquiler> findAllDespachados();
	
	@Query("FROM Alquiler o WHERE o.estado = 'A' ORDER BY o.fecha DESC")
	List<Alquiler> findAllAnulados();
	
	@Query("FROM Alquiler o WHERE o.estado = 'R' AND o.fecha BETWEEN :fechaInicio AND :fechaFin ORDER BY o.fecha DESC")
	List<Alquiler> findAllRecibidosWithRangoFechas(@Param("fechaInicio") Date fechaInicio,@Param("fechaFin") Date fechaFin);
	
	@Query("FROM Alquiler o WHERE o.estado = 'D' AND o.fechaDespacho BETWEEN :fechaInicio AND :fechaFin ORDER BY o.fecha DESC")
	List<Alquiler> findAllDespachadosWithRangoFechas(@Param("fechaInicio") Date fechaInicio,@Param("fechaFin") Date fechaFin);
	
	@Query("FROM Alquiler o WHERE o.estado = 'A' AND o.fecha BETWEEN :fechaInicio AND :fechaFin ORDER BY o.fecha DESC")
	List<Alquiler> findAllAnuladosWithRangoFechas(@Param("fechaInicio") Date fechaInicio,@Param("fechaFin") Date fechaFin);
}