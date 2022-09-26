package com.empresa.administracion.apirest.services.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.administracion.apirest.models.dao.IEmpleadoDAO;
import com.empresa.administracion.apirest.models.entities.Empleado;
import com.empresa.adminstracion.apirest.services.interfaces.IEmpleadoService;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {
	
	@Autowired
	private IEmpleadoDAO empleadoDAO;
	
	@Override
	public List<Empleado> findAll() {
		return (List<Empleado>)empleadoDAO.findAll();
	}

	@Override
	public Empleado findById(Long id) {
		return empleadoDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Empleado save(Empleado empleado) {
		return empleadoDAO.save(empleado);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		empleadoDAO.deleteById(id);
	}

	@Override
	public List<Empleado> isExist(Empleado empleado) {
		return empleadoDAO.findByNombreEmail(empleado);
	}
}
