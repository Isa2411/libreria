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

import com.sena.libreria.IService.IMultaService;
import com.sena.libreria.models.multa;


@RequestMapping("/api/libreria/multa")
@RestController
@CrossOrigin
public class multaController {
	
	@Autowired
	private IMultaService multaService;
	
	@PostMapping("/")
    public ResponseEntity<Object> save(@RequestBody multa multa) {


        if (multa.getUsua_multado().equals("")) {
            
            return new ResponseEntity<>("EL usuario multado es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }


        if (multa.getValor_multa().equals("")) {
            
            return new ResponseEntity<>("El valor de la multa es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (multa.getFecha_multa().equals("")) {
            
            return new ResponseEntity<>("La fecha de la multa es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        
        if (multa.getEstado_multa().equals("")) {
            
            return new ResponseEntity<>("El estado de la multa es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        
        multaService.save(multa);
        return new ResponseEntity<>(multa, HttpStatus.OK);

    }
	
	@GetMapping("/")
	public ResponseEntity<Object> findAll(){
		var ListaMulta=multaService.findAll();
		return new ResponseEntity<>(ListaMulta,HttpStatus.OK);
	}
	
	@GetMapping("/{id_multa}")
	public ResponseEntity<Object> findOne(@PathVariable String id_multa){
		var multa=multaService.findOne(id_multa);
		return new ResponseEntity<>(multa,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id_multa}")
	public ResponseEntity<Object> delete(@PathVariable String id_multa){
		multaService.delete(id_multa);
		return new ResponseEntity<>("Registro eliminado",HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable String id, @ModelAttribute("multa") multa multaUpdate){
		var multa=multaService.findOne(id).get();
		if (multa != null) {
			multa.setUsua_multado(multaUpdate.getUsua_multado());
			multa.setValor_multa(multaUpdate.getValor_multa());
			multa.setFecha_multa(multaUpdate.getFecha_multa());
			multa.setEstado_multa(multaUpdate.getEstado_multa());

			multaService.save(multa);
			return new ResponseEntity<>(multa,HttpStatus.OK);
		}
		else {
		return new ResponseEntity<>("Error, libro no encontrado",HttpStatus.BAD_REQUEST);
		}
	}

}
