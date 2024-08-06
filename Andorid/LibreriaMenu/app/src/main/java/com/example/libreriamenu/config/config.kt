package com.example.libreriamenu.config

class config {
    /*Se crea una url static, para consultar sin instanciar
    metodo companion object sirve para almacenar las variables static
     */
    companion object{
        //ejemplo: http://dirección-ip:puerto/
        val urlBase="http://10.192.64.66:8080/api/libreria/"
        val urlLibro = urlBase + "libro/"
    }
}