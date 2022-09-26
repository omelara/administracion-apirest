 package com.empresa.adminstracion.apirest.services.interfaces;

import java.util.List;

import com.empresa.administracion.apirest.models.entities.Maquina;

public interface IMaquinaService {
	
    public List<Maquina> findAllActivas();
    
    public List<Maquina> findAllInactivas();
	
	public Maquina findById(Long id);
	
	public Maquina save(Maquina maquina);
	
	public Maquina changeEstate(Maquina maquina);
	
	public List<Maquina> isExist(Maquina maquina);


}
