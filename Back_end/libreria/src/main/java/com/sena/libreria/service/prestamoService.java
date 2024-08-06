package com.sena.libreria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.libreria.IService.IPrestamoService;
import com.sena.libreria.interfaces.IPrestamo;
import com.sena.libreria.models.prestamo;

@Service
public class prestamoService implements IPrestamoService{
	
	@Autowired
	private IPrestamo data;
	
	@Override
	public String save(prestamo prestamo) {
		data.save(prestamo);
		return prestamo.getId_prestamo();
	}


	@Override
	public List<prestamo> findAll() {
		List<prestamo> ListaPrestamo=
		(List<prestamo>) data.findAll();
		return ListaPrestamo;
	}

	@Override
	public Optional<prestamo> findOne(String id) {
		Optional<prestamo> prestamo=data.findById(id);
		return prestamo;
	}


	@Override
	public int delete(String id_prestamo) {
	    try {
	        data.deleteById(id_prestamo);
	        return 1;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0;
	    }
	}

}
