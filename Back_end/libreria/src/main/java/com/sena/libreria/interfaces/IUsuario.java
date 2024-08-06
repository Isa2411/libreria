package com.sena.libreria.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.sena.libreria.models.usuario;

@Repository
public interface IUsuario extends CrudRepository<usuario, String>{
	@Query("SELECT u FROM usuario u WHERE u.nombre_usuario LIKE %?1% OR u.correo_usuario LIKE %?1%")
	List<usuario> filtroUsuario(String filtro);
}
