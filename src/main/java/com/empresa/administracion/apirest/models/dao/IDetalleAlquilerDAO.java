package com.empresa.administracion.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.empresa.administracion.apirest.models.entities.DetalleAlquiler;

public interface IDetalleAlquilerDAO extends CrudRepository<DetalleAlquiler, Long> {

} 