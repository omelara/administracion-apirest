 package com.empresa.administracion.apirest.models.dao;

import java.util.List;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.empresa.administracion.apirest.models.entities.Empleado;

public interface IEmpleadoDAO extends CrudRepository<Empleado,Long>{
	
	@Query("FROM Empleado e WHERE e.nombre=:#{#empleado.nombre} and e.correo=:#{#empleado.correo}")
	List<Empleado> findByNombreEmail(Empleado empleado);
	
	
}

