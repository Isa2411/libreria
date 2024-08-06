package com.sena.libreria.IService;

import java.util.List;
import java.util.Optional;

import com.sena.libreria.models.libro;

public interface ILibroService {

	public String save(libro libro);
	public List<libro> findAll();
	public Optional<libro> findOne(String id_libro);
	public int delete(String id_libro);
	public List<libro> filtroLibro(String filtro);

}
