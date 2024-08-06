package com.sena.libreria.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="libro")
public class libro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id_libro", nullable=false, length = 36)
    private String id_libro;
    
    @Column(name="titulo", nullable=false, length = 50)
    private String titulo;
    
    @Column(name="autor", nullable=false, length = 100)
    private String autor;
    
    @Column(name="isbn", nullable=false, length = 14)
    private String isbn;
    
    @Column(name="genero", nullable=false, length = 25)
    private String genero;
    
    @Column(name="num_ejemplares_dispo", nullable=false, length = 3000)
    private String num_ejemplares_dispo;
    
    @Column(name="num_ejemplares_ocupados", nullable=false, length = 3000)
    private String num_ejemplares_ocupados;

	public libro() {
		super();
	}

	public libro(String id_libro, String titulo, String autor, String isbn, String genero, String num_ejemplares_dispo,
			String num_ejemplares_ocupados) {
		super();
		this.id_libro = id_libro;
		this.titulo = titulo;
		this.autor = autor;
		this.isbn = isbn;
		this.genero = genero;
		this.num_ejemplares_dispo = num_ejemplares_dispo;
		this.num_ejemplares_ocupados = num_ejemplares_ocupados;
	}

	public String getId_libro() {
		return id_libro;
	}

	public void setId_libro(String id_libro) {
		this.id_libro = id_libro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getNum_ejemplares_dispo() {
		return num_ejemplares_dispo;
	}

	public void setNum_ejemplares_dispo(String num_ejemplares_dispo) {
		this.num_ejemplares_dispo = num_ejemplares_dispo;
	}

	public String getNum_ejemplares_ocupados() {
		return num_ejemplares_ocupados;
	}

	public void setNum_ejemplares_ocupados(String num_ejemplares_ocupados) {
		this.num_ejemplares_ocupados = num_ejemplares_ocupados;
	}
	
	
}
