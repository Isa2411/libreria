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

import com.sena.libreria.IService.ILibroService;
import com.sena.libreria.models.libro;


@RequestMapping("/api/libreria/libro")
@RestController
@CrossOrigin
public class libroController {
	
	@Autowired
	private ILibroService libroService;
	
	@PostMapping("/")
    public ResponseEntity<Object> save(@RequestBody libro libro) {


        if (libro.getTitulo().equals("")) {
            
            return new ResponseEntity<>("El título del libro es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (libro.getAutor().equals("")) {
            
            return new ResponseEntity<>("El nombre del autor es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (libro.getIsbn().equals("")) {
            
            return new ResponseEntity<>("El ISBN es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (libro.getGenero().equals("")) {
            
            return new ResponseEntity<>("El genero es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        
        if (libro.getNum_ejemplares_dispo().equals("")) {
            
            return new ResponseEntity<>("El numero de ejemplares disponibles es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        
        if (libro.getNum_ejemplares_ocupados().equals("")) {
            
            return new ResponseEntity<>("El numero de ejemplares ocupados es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        
        libroService.save(libro);
        return new ResponseEntity<>(libro, HttpStatus.OK);

    }
	
	@GetMapping("/")
	public ResponseEntity<Object> findAll(){
		var ListaLibro=libroService.findAll();
		return new ResponseEntity<>(ListaLibro,HttpStatus.OK);
	}
	
	@GetMapping("/{id_libro}")
	public ResponseEntity<Object> findOne(@PathVariable String id_libro){
		var libro=libroService.findOne(id_libro);
		return new ResponseEntity<>(libro,HttpStatus.OK);
	}
	
	@GetMapping("/busqueda/{filtro}")
	public ResponseEntity<Object> findFiltro(@PathVariable String filtro){
	var ListaLibro=libroService.filtroLibro(filtro); 
	return new ResponseEntity<>(ListaLibro,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id_libro}")
	public ResponseEntity<Object> delete(@PathVariable String id_libro){
		libroService.delete(id_libro);
		return new ResponseEntity<>("Registro eliminado",HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable String id, @ModelAttribute("libro") libro libroUpdate){
		var libro=libroService.findOne(id).get();
		if (libro != null) {
			libro.setTitulo(libroUpdate.getTitulo());
			libro.setAutor(libroUpdate.getAutor());
			libro.setIsbn(libroUpdate.getIsbn());
			libro.setGenero(libroUpdate.getGenero());
			libro.setNum_ejemplares_dispo(libroUpdate.getNum_ejemplares_dispo());
			libro.setNum_ejemplares_ocupados(libroUpdate.getNum_ejemplares_ocupados());

			libroService.save(libro);
			return new ResponseEntity<>(libro,HttpStatus.OK);
		}
		else {
		return new ResponseEntity<>("Error, libro no encontrado",HttpStatus.BAD_REQUEST);
		}
	}
}
