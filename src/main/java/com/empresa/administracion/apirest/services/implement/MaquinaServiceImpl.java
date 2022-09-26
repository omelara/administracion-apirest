package com.empresa.administracion.apirest.services.implement;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.administracion.apirest.models.dao.IMaquinaDAO;
import com.empresa.administracion.apirest.models.entities.Maquina;
import com.empresa.adminstracion.apirest.services.interfaces.IMaquinaService;

@Service
public class MaquinaServiceImpl implements IMaquinaService {
	
	@Autowired
	private IMaquinaDAO maquinaDAO;
	
	@Override
	public List<Maquina> findAllActivas() {
		return maquinaDAO.findAll();
	}

	@Override
	public List<Maquina> findAllInactivas() {
		return maquinaDAO.findAllInactivas();
	}

	@Override
	public Maquina findById(Long id) {
		return maquinaDAO.findById(id).orElse(null);
	}

	@Override
	public Maquina save(Maquina maquina) {
		return maquinaDAO.save(maquina);
	}

	@Override
	public Maquina changeEstate(Maquina maquina) {
		return maquinaDAO.save(maquina);
	}
	
	@Override
	public List<Maquina> isExist(Maquina maquina) {
		return maquinaDAO.findByNombreDescripcion(maquina);
	}

}
