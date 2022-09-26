 package com.empresa.administracion.apirest.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.administracion.apirest.models.entities.Alquiler;
import com.empresa.adminstracion.apirest.services.interfaces.IAlquilerService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api")
public class AlquilerController {
	
	@Autowired
	private IAlquilerService alquilerService;
	
	@GetMapping("/alquileres")
	public List<Alquiler> getAllRecibidos(@RequestParam(name = "fecha", required = false) Date fecha){
		return alquilerService.findAll(fecha);
	}
	@GetMapping("/alquileres/despachados")
	public List<Alquiler> getAllDespachados(@RequestParam(name = "fecha", required = false) Date fecha){
		return alquilerService.findAllDespachados(fecha);
	}
	@GetMapping("/alquileres/anulados")
	public List<Alquiler> getAllAnulados(@RequestParam(name = "fecha", required = false) Date fecha){
		return alquilerService.findAllAnulados(fecha);
	}
	
	@GetMapping("/alquileres/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Alquiler alquiler = null;
		Map<String, Object> response = new HashMap<>();
		try {
			alquiler = alquilerService.findById(id);
		}catch(DataAccessException e) {
			response.put("message", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage());
		}
		if(alquiler == null) {
			response.put("message", "El alquiler con ID: " .concat(id.toString().concat(" No existen en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Alquiler>(alquiler,HttpStatus.OK);
	}
	
	@PostMapping("/alquileres")
	public ResponseEntity<?> saveOrUpdate(@RequestBody Alquiler alquiler){
		Map<String, Object> response = new HashMap<>();
		try {
			alquilerService.saveOrUpdate(alquiler);
		}catch(DataAccessException e) {
			response.put("error", "Error al insertar alquiler en la base de datos: "+e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Alquiler registrado con exito...");
		response.put("alquiler", alquiler);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/alquileres/change-state")
	public ResponseEntity<?> changeState(@RequestBody Alquiler alquiler, @RequestParam(name = "estado") String estado){
			
		Map<String, Object> response = new HashMap<>();
			try {
				alquiler.setEstado(estado);
				if(estado.equals("D")) {
					Date fechaDesp = new Date();
					alquiler.setFechaDespacho(fechaDesp);
				}
				alquilerService.saveOrUpdate(alquiler);
				
			}catch(DataAccessException e) {
				response.put("message", "Error al cambiar estado de alquiler");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			if(estado.equals("D"))
				estado = "Despachado";
			else
				estado = "Anulado";
			response.put("message", "El estado del alquiler ha sido cambiado a: "+estado.toString());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
