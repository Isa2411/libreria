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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.libreria.IService.IUsuarioService;
import com.sena.libreria.models.usuario;

@RequestMapping("/api/libreria/usuario")
@RestController
@CrossOrigin
public class usuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@PostMapping("/")
    public ResponseEntity<Object> save(@ModelAttribute("usuario") usuario usuario) {


        if (usuario.getNombre_usuario().equals("")) {
            
            return new ResponseEntity<>("El nombre es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (usuario.getDireccion_usuario().equals("")) {
            
            return new ResponseEntity<>("La dirección es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (usuario.getCorreo_usuario().equals("")) {
            
            return new ResponseEntity<>("El correo es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (usuario.getTipo_usuario().equals("")) {
            
            return new ResponseEntity<>("El tipo de usuario es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        
        usuarioService.save(usuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);

    }
	
	@GetMapping("/")
	public ResponseEntity<Object> findAll(){
		var ListaUsuario=usuarioService.findAll();
		return new ResponseEntity<>(ListaUsuario,HttpStatus.OK);
	}
	
	@GetMapping("/{id_usuario}")
	public ResponseEntity<Object> findOne(@PathVariable String id_usuario){
		var usuario=usuarioService.findOne(id_usuario);
		return new ResponseEntity<>(usuario,HttpStatus.OK);
	}
	
	@GetMapping("/busqueda/{filtro}")
	public ResponseEntity<Object> findFiltro(@PathVariable String filtro){
	var ListaUsuario=usuarioService.filtroUsuario(filtro); 
	return new ResponseEntity<>(ListaUsuario,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id_usuario}")
	public ResponseEntity<Object> delete(@PathVariable String id_usuario){
		usuarioService.delete(id_usuario);
		return new ResponseEntity<>("Registro eliminado",HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable String id, @ModelAttribute("usuario") usuario usuarioUpdate){
		var usuario=usuarioService.findOne(id).get();
		if (usuario != null) {
			usuario.setNombre_usuario(usuarioUpdate.getNombre_usuario());
			usuario.setDireccion_usuario(usuarioUpdate.getDireccion_usuario());
			usuario.setCorreo_usuario(usuarioUpdate.getCorreo_usuario());
			usuario.setTipo_usuario(usuarioUpdate.getTipo_usuario());

			usuarioService.save(usuario);
			return new ResponseEntity<>(usuario,HttpStatus.OK);
		}
		else {
		return new ResponseEntity<>("Error, usuario no encontrado",HttpStatus.BAD_REQUEST);
		}
}
}
	
