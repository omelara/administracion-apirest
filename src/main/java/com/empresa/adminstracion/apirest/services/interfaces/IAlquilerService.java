package com.empresa.adminstracion.apirest.services.interfaces;

import java.util.Date;


import java.util.List;

import com.empresa.administracion.apirest.models.entities.Alquiler;

public interface IAlquilerService {
	
	public List<Alquiler> findAll(Date fecha);
	
	public List<Alquiler> findAllDespachados(Date fecha);
	
	public List<Alquiler> findAllAnulados(Date fecha);
	
	public Alquiler saveOrUpdate(Alquiler orden);
	
	public Alquiler changeState(Alquiler orden);
	
	public Alquiler findById(Long id);

	public List<Alquiler> findAll();

	public List<Alquiler> findByNombre(Object nombre);

	public Alquiler save(Alquiler alquiler);

	public void delete(Long id);

}
 