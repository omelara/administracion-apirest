 package com.empresa.administracion.apirest.models.dao;

import java.util.List;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.empresa.administracion.apirest.models.entities.Categoria;

public interface ICategoriaDAO extends CrudRepository<Categoria,Long>{
	
	//@Query("FROM Categoria c WHERE c.nombre=:#{#categoria.nombre}")
	List<Categoria> findByNombreIgnoreCase(String cadena);

}