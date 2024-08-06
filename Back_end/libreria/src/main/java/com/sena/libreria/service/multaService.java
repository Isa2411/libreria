package com.sena.libreria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.libreria.IService.IMultaService;
import com.sena.libreria.interfaces.IMulta;
import com.sena.libreria.models.multa;

@Service
public class multaService implements IMultaService{
	
	@Autowired
	private IMulta data;
	
	@Override
	public String save(multa multa) {
		data.save(multa);
		return multa.getId_multa();
	}


	@Override
	public List<multa> findAll() {
		List<multa> ListaMulta=
		(List<multa>) data.findAll();
		return ListaMulta;
	}

	@Override
	public Optional<multa> findOne(String id) {
		Optional<multa> multa=data.findById(id);
		return multa;
	}


	@Override
	public int delete(String id_multa) {
	    try {
	        data.deleteById(id_multa);
	        return 1;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0;
	    }
	}

	
}
