package com.sena.libreria.IService;

import java.util.List;
import java.util.Optional;

import com.sena.libreria.models.multa;

public interface IMultaService {
	public String save(multa multa);
	public List<multa> findAll();
	public Optional<multa> findOne(String id_multa);
	public int delete(String id_multa);
}
