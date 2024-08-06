package com.sena.libreria.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sena.libreria.models.prestamo;

@Repository
public interface IPrestamo extends CrudRepository<prestamo, String>{
	
}
