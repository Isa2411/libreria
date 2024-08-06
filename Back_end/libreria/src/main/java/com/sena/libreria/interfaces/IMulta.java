package com.sena.libreria.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sena.libreria.models.multa;

@Repository
public interface IMulta extends CrudRepository<multa, String> {
	
}
