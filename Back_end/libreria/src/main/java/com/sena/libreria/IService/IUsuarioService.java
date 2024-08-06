package com.sena.libreria.IService;

import java.util.List;
import java.util.Optional;

import com.sena.libreria.models.usuario;

public interface IUsuarioService {
	public String save(usuario usuario);
	public List<usuario> findAll();
	public Optional<usuario> findOne(String id_usuario);
	public int delete(String id_usuario);
	public List<usuario> filtroUsuario(String filtro);
}
