package com.sena.libreria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.libreria.IService.IPrestamoService;
import com.sena.libreria.models.prestamo;

@RequestMapping("/api/libreria/prestamo")
@RestController
@CrossOrigin
public class prestamoController {
	
	@Autowired
	private IPrestamoService prestamoService;
	
	@PostMapping("/")
    public ResponseEntity<Object> save(@RequestBody prestamo prestamo) {


        if (prestamo.getFecha_prestamo().equals("")) {
            
            return new ResponseEntity<>("La fecha del prestamo es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (prestamo.getFecha_devo().equals("")) {
            
            return new ResponseEntity<>("La fecha de la devolucion es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (prestamo.getUsua_prestamo().equals("")) {
            
            return new ResponseEntity<>("El nombre del usuario que realizo el prestamo es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        
        if (prestamo.getLibro_prestado().equals("")) {
            
            return new ResponseEntity<>("El libro prestado es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        
        if (prestamo.getEstado_prestamo().equals("")) {
            
            return new ResponseEntity<>("El estado es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        
        prestamoService.save(prestamo);
        return new ResponseEntity<>(prestamo, HttpStatus.OK);

    }
	
	@GetMapping("/")
	public ResponseEntity<Object> findAll(){
		var ListaPrestamo=prestamoService.findAll();
		return new ResponseEntity<>(ListaPrestamo,HttpStatus.OK);
	}
	
	@GetMapping("/{id_prestamo}")
	public ResponseEntity<Object> findOne(@PathVariable String id_prestamo){
		var prestamo=prestamoService.findOne(id_prestamo);
		return new ResponseEntity<>(prestamo,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id_prestamo}")
	public ResponseEntity<Object> delete(@PathVariable String id_prestamo){
		prestamoService.delete(id_prestamo);
		return new ResponseEntity<>("Registro eliminado",HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable String id, @ModelAttribute("prestamo") prestamo prestamoUpdate){
		var prestamo=prestamoService.findOne(id).get();
		if (prestamo != null) {
			prestamo.setFecha_prestamo(prestamoUpdate.getFecha_prestamo());
			prestamo.setFecha_devo(prestamoUpdate.getFecha_devo());
			prestamo.setUsua_prestamo(prestamoUpdate.getUsua_prestamo());
			prestamo.setLibro_prestado(prestamoUpdate.getLibro_prestado());
			prestamo.setEstado_prestamo(prestamoUpdate.getEstado_prestamo());

			prestamoService.save(prestamo);
			return new ResponseEntity<>(prestamo,HttpStatus.OK);
		}
		else {
		return new ResponseEntity<>("Error, libro no encontrado",HttpStatus.BAD_REQUEST);
		}
	}
}
