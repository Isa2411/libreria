package com.sena.libreria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.libreria.IService.ILibroService;
import com.sena.libreria.interfaces.ILibro;
import com.sena.libreria.models.libro;

@Service
public class libroService implements ILibroService{
	
	@Autowired
	private ILibro data;
	
	@Override
	public String save(libro libro) {
		data.save(libro);
		return libro.getId_libro();
	}


	@Override
	public List<libro> findAll() {
		List<libro> ListaLibro=
		(List<libro>) data.findAll();
		return ListaLibro;
	}

	@Override
	public Optional<libro> findOne(String id) {
		Optional<libro> libro=data.findById(id);
		return libro;
	}


	@Override
	public int delete(String id_libro) {
	    try {
	        data.deleteById(id_libro);
	        return 1;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0;
	    }
	}


	@Override
	public List<libro> filtroLibro(String filtro) {
		List<libro> ListaLibro=data.filtroLibro(filtro);
		return ListaLibro;
	}
	
}
