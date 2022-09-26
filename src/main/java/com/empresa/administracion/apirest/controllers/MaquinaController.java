package com.empresa.administracion.apirest.controllers;

import java.io.File;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.empresa.administracion.apirest.models.entities.Maquina;
import com.empresa.adminstracion.apirest.services.interfaces.IMaquinaService;
import com.empresa.adminstracion.apirest.services.interfaces.IUploadFileService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api")
public class MaquinaController {
	
	@Autowired
	private IMaquinaService maquinaService;

	@Autowired
	private IUploadFileService uploadService;

	private Logger logger = LoggerFactory.getLogger(MaquinaController.class);

	@Secured({"ROLE_ADMIN"})
	@GetMapping("/maquinas/activas")
	public List<Maquina> getAllActivas() {
		return maquinaService.findAllActivas();
	}

	@Secured({"ROLE_ADMIN"})
	@GetMapping("/maquinas/inactivas")
	public List<Maquina> getAllInactivas() {
		return maquinaService.findAllInactivas();
	}

	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/maquinas/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Maquina maquina = null;
		Map<String, Object> response = new HashMap<>();
		try {
			maquina = maquinaService.findById(id);
		} catch (DataAccessException e) {
			response.put("message", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage());
		}
		if (maquina == null) {
			response.put("message",
					"La maquina con ID: ".concat(id.toString().concat(" No existen en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Maquina>(maquina, HttpStatus.OK);
	}

	@Secured({"ROLE_ADMIN"})
	@PostMapping("/maquinas")
	public ResponseEntity<?> save(@RequestPart Maquina maquina,
			@RequestPart(name = "imagen", required = false) MultipartFile imagen) throws IOException {
		String imageNewName = "";
		Map<String, Object> response = new HashMap<>();
		try {
			if (maquinaService.isExist(maquina).size() > 0 && maquina.getId() == null) {
				response.put("message", "Ya existe un registro con este nombre y descripcion en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
			} else {
				if (imagen != null)
					imageNewName = uploadService.copyFile(imagen);
				maquina.setImagen(imageNewName);
				maquinaService.save(maquina);
			}

		} catch (DataAccessException e) {
			response.put("message", "Error al insertar registro en la base de datos");
			logger.error("ERROR: ".concat(e.getMessage()));
			uploadService.deleteFile(imageNewName);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("message", "Maquina registrada con exito...");
		response.put("maquina", maquina);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@Secured({"ROLE_ADMIN"})
	@PutMapping("/maquinas/{id}")
	public ResponseEntity<?> update(@RequestPart Maquina maquina, @PathVariable Long id,
			@RequestPart(name = "imagen", required = false) MultipartFile imagen) throws IOException {

		String imageNewName = null;
		Maquina maquinaActual = maquinaService.findById(id);
		Maquina maquinaUpdated = null;
		Map<String, Object> response = new HashMap<>();
		if (maquinaActual == null) {
			response.put("message", "Error: no se puede editar, la maquina con ID: "
					.concat(id.toString().concat("no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			maquinaActual.setNombre(maquina.getNombre());
			maquinaActual.setDescripcion(maquina.getDescripcion());
			maquinaActual.setPrecio(maquina.getPrecio());
			maquinaActual.setExistencia(maquina.getExistencia());
			maquinaActual.setCategoria(maquina.getCategoria());
			if (maquinaActual.getImagen() != null && maquinaActual.getImagen().length() > 0) {
				String imgAnterior = maquinaActual.getImagen();
				Path rutaImgAnterior = uploadService.getPath(imgAnterior);
				File archivoImgAnterior = rutaImgAnterior.toFile();
				if (archivoImgAnterior.exists() && archivoImgAnterior.canRead()) {
					archivoImgAnterior.delete();
				}

			}
			if (imagen != null)
				imageNewName = uploadService.copyFile(imagen);
			maquinaActual.setImagen(imageNewName);
			maquinaActual = maquinaService.save(maquinaActual);

		} catch (DataAccessException e) {
			response.put("message", "Error al actualizar la maquina");
			logger.error("ERROR: ".concat(e.getMessage()));
			uploadService.deleteFile(imageNewName);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Maquina actualizada con exito..");
		response.put("maquina", maquinaUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@Secured({"ROLE_ADMIN"})
	@PutMapping("/maquinas/change-state")
	public ResponseEntity<?> changeState(@RequestBody Maquina maquina, @RequestParam(name = "estado") String estado) {

		Map<String, Object> response = new HashMap<>();
		try {
			maquina.setEstado(estado);
			maquinaService.save(maquina);

		} catch (DataAccessException e) {
			response.put("message", "Error al cambiar estado de la maquina");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "El estado de la maquina ha sido cambiado a" + estado.toString());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@Secured({"ROLE_ADMIN"})
	@PostMapping("/imagen/delete/{name}")
	public ResponseEntity<?> deleteImagen(@PathVariable(name = "name") String imagen) {
		if (uploadService.deleteFile(imagen))
			return ResponseEntity.ok().build();
		else
			return ResponseEntity.status(500).build();
	}
} 