package com.empresa.administracion.apirest.models.dao;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.empresa.administracion.apirest.models.entities.Maquina;

public interface IMaquinaDAO extends CrudRepository<Maquina, Long> {
	
	@Query("FROM Maquina m WHERE m.nombre=:#{#maquina.nombre} and m.descripcion=:#{#maquina.descripcion}")
	List<Maquina> findByNombreDescripcion(Maquina maquina);
	
	@Query("FROM Maquina p WHERE p.estado= 'D' ORDER BY p.id DESC")
	List<Maquina> findAll();
	
	@Query("FROM Maquina p WHERE p.estado= 'I' ORDER BY p.id DESC")
	List<Maquina> findAllInactivas();
	

} 