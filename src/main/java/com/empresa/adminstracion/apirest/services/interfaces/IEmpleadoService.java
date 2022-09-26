  package com.empresa.adminstracion.apirest.services.interfaces;

import java.util.List;

import com.empresa.administracion.apirest.models.entities.Empleado;

public interface IEmpleadoService {
	
	public List<Empleado> findAll();
	
	public Empleado findById(Long id);
	
	public Empleado save(Empleado empleado);
	
	public void delete(Long id);
	
	public List<Empleado> isExist(Empleado empleado);

}

