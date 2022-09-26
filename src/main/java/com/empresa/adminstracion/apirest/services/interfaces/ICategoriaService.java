 package com.empresa.adminstracion.apirest.services.interfaces;

import java.util.List;

import com.empresa.administracion.apirest.models.entities.Categoria;

public interface ICategoriaService {
	
	public List<Categoria> findAll();
	
	public Categoria findById(Long id);
	
	public Categoria save(Categoria categoria);
	
	public void delete(Long id);
	
	public List<Categoria> findByNombre(String nombre);

}