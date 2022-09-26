package com.empresa.adminstracion.apirest.services.interfaces;

import com.empresa.administracion.apirest.models.entities.Usuario;

public interface IUsuarioService {

	public Usuario findByuserName(String username);
}
