package com.empresa.administracion.apirest.services.implement;

import java.util.ArrayList;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.administracion.apirest.models.dao.IAlquilerDAO;
import com.empresa.administracion.apirest.models.dao.IDetalleAlquilerDAO;
import com.empresa.administracion.apirest.models.entities.Alquiler;
import com.empresa.administracion.apirest.models.entities.DetalleAlquiler;
import com.empresa.adminstracion.apirest.services.interfaces.IAlquilerService;

@Service
public class AlquilerServiceImpl implements IAlquilerService{

	@Autowired
	private IAlquilerDAO alquilerDAO;
	
	@Autowired
	private IDetalleAlquilerDAO detalleAlquilerDAO;
	
	@Override
	@Transactional(readOnly = true)
	public List<Alquiler> findAll(Date fechaInicio) {
		Date fechaFin = null;
		if(fechaInicio != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(fechaInicio);
			c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			fechaFin = c.getTime();
			return alquilerDAO.findAllRecibidosWithRangoFechas(fechaInicio, fechaFin);
		}
		return alquilerDAO.findAllRecibidos();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Alquiler> findAllDespachados(Date fechaInicio) {
		Date fechaFin = null;
		if(fechaInicio != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(fechaInicio);
			c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			fechaFin = c.getTime();
			return alquilerDAO.findAllDespachadosWithRangoFechas(fechaInicio, fechaFin);
		}
		return alquilerDAO.findAllDespachados();
	}

	
	@Override
	@Transactional(readOnly = true)
	public List<Alquiler> findAllAnulados(Date fechaInicio) {
		Date fechaFin = null;
		if(fechaInicio != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(fechaInicio);
			c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			fechaFin = c.getTime();
			return alquilerDAO.findAllAnuladosWithRangoFechas(fechaInicio, fechaFin);
		}
		return alquilerDAO.findAllAnulados();
	}

	@Transactional
	@Override
	public Alquiler saveOrUpdate(Alquiler alquiler) {
		Alquiler alquilerPersisted = null;
		try {
			if(alquiler.getId() == null) {
				List<DetalleAlquiler> detalleAlquiler = alquiler.getDetalleAlquiler();
				alquiler.setDetalleAlquiler(new ArrayList<DetalleAlquiler>());
				alquilerPersisted = alquilerDAO.save(alquiler);
				
				for(DetalleAlquiler detalle: detalleAlquiler) {
					detalle.setAlquiler(alquilerPersisted);
				}
				detalleAlquilerDAO.saveAll(detalleAlquiler);
				
			}else {
				for(DetalleAlquiler detalle: alquiler.getDetalleAlquiler()) {
					detalle.setAlquiler(alquiler);
				}
				alquilerDAO.save(alquiler);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return alquilerPersisted;
	}

	@Override
	public Alquiler changeState(Alquiler alquiler) {
		return alquilerDAO.save(alquiler);
	}

	@Override
	public Alquiler findById(Long id) {
		return alquilerDAO.findById(id).orElse(null);
	}

	@Override
	public List<Alquiler> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Alquiler> findByNombre(Object nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Alquiler save(Alquiler alquiler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
