package com.sena.libreria.IService;

import java.util.List;
import java.util.Optional;

import com.sena.libreria.models.prestamo;



public interface IPrestamoService {
	public String save(prestamo prestamo);
	public List<prestamo> findAll();
	public Optional<prestamo> findOne(String id_prestamo);
	public int delete(String id_prestamo);
}
