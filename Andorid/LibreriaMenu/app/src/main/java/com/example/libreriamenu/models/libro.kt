package com.example.libreriamenu.models

data class libro(
    var id_Libro:String,
    var titulo:String,
    var autor: String,
    var isbn: String,
    var genero: String,
    var num_ejemplares_disponibles: Int,
    var num_ejemplares_ocupados: Int
)
