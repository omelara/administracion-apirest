package com.empresa.administracion.apirest.controllers;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.administracion.apirest.models.entities.Empleado;
import com.empresa.adminstracion.apirest.services.interfaces.IEmpleadoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class EmpleadoController {

	@Autowired
	private IEmpleadoService empleadoService;
	
	@GetMapping("/empleados")
	public List<Empleado> getAll(){
		return empleadoService.findAll();
	}
	
	@GetMapping("/empleados/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Empleado empleado = null;
		Map<String, Object> response = new HashMap<>();
		try {
			empleado = empleadoService.findById(id);
		}catch(DataAccessException e) {
			response.put("message", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage());
		}
		if(empleado == null) {
			response.put("message", "El empleado con ID: ".concat(id.toString().concat("No existen en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Empleado>(empleado,HttpStatus.OK);
	}
	@PostMapping("/empleados")
	public ResponseEntity<?> save( @RequestBody Empleado empleado, BindingResult result){
		
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo'"+err.getField()+"' "+err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("error", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			if(empleadoService.isExist(empleado).size() > 0) {
				response.put("message", "Ya existe un registro con este nombre y correo en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CONFLICT);
			}else {
				empleadoService.save(empleado);
			}
			
		}catch(DataAccessException e){
			response.put("message", "Error al insertar registro en la base de datos");
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
			
		}
		response.put("message", "Empleado registrado con exito...");
		response.put("empleado", empleado);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	// Actualizar cliente
	@PutMapping("/empleados/{id}")
	public ResponseEntity<?> update(@RequestBody Empleado empleado, @PathVariable Long id){
		
		Empleado empleadoActual = empleadoService.findById(id);
		Empleado empleadoUpdated = null;
		Map<String, Object> response = new HashMap<>();
			if(empleadoActual == null) {
				response.put("message", "Error: no se puede editar, el cliente ID:".concat(id.toString().concat("No existe en la base de datos")));
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
			}
			try {
				empleadoActual.setNombre(empleado.getNombre());
				empleadoActual.setTelefono(empleado.getTelefono());
				empleadoActual.setCorreo(empleado.getCorreo());
				empleadoActual.setDireccion(empleado.getDireccion());
				empleadoActual.setCreateAt(empleado.getCreateAt());
				empleadoActual.setEstado(empleado.getEstado());
				empleadoUpdated = empleadoService.save(empleadoActual);
				
			}catch(DataAccessException e) {
				response.put("message", "Error al actualizar el empleado");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			response.put("message", "Empleado actualizado con exito..");
			response.put("empleado", empleadoUpdated);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	@DeleteMapping("/empleados/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		
		try {
			empleadoService.delete(id);
		}catch(DataAccessException e) {
			response.put("message", "Error al eliminar el empleado de la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Empleado eliminado con exito...");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}
}
