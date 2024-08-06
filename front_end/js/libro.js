var url = "http://localhost:8080/api/libreria/libro/";

/*document.getElementById("titulo").addEventListener("keypress", soloLetras);

const letrasPermitidas= [
    'a', 'b', 'c', 'A', 'B', 'C', 'á', 'Á', 'ñ', 'Ñ', ' '
]

const numerosPermitidos= [
    '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', ' '
]

const caracteresPermitidos= [
    '@', '-', '_', ' ', '.'
]
*/
/**
 * 
 * Este método solo permite letras 
 */
/*
function sololetras(event){
    console.log("LLave presionada: " + event.key);
    console.log("Código tecla: " + event.keyCode);

    if (!(letrasPermitidas.includes(event.key))){
        event.preventDefault()
        return;
    }
}*/

function listarLibros() {
    var urlLocal = url;
    var filtro = document.getElementById("texto").value;
    if (filtro !== "") {
        urlLocal += "busqueda/" + filtro;
    }

    $.ajax({
        url: urlLocal,
        type: "GET",
        success: function(result) {
            console.log(result);

            var cuerpoTablaLibros = document.getElementById("cuerpoTablaLibros");
            cuerpoTablaLibros.innerHTML = "";
            for (var i = 0; i < result.length; i++) {
                var trResgistro = document.createElement("tr");

                var celdaId = document.createElement("td");
                celdaId.innerText = result[i]["id_libro"];

                var celdaTitulo = document.createElement("td");
                celdaTitulo.innerText = result[i]["titulo"];

                var celdaAutor = document.createElement("td");
                celdaAutor.innerText = result[i]["autor"];

                var celdaISBN = document.createElement("td");
                celdaISBN.innerText = result[i]["isbn"];

                var celdaGenero = document.createElement("td");
                celdaGenero.innerText = result[i]["genero"];

                var celdaEjemplaresDisponibles = document.createElement("td");
                celdaEjemplaresDisponibles.innerText = result[i]["num_ejemplares_dispo"];

                var EjemplaresOcupados = document.createElement("td");
                EjemplaresOcupados.innerText = result[i]["num_ejemplares_ocupados"];

                var celdaOpcion = document.createElement("td");
                var botonActualizar = document.createElement("button");
                botonActualizar.value = result[i]["id_libro"];
                botonActualizar.innerHTML = "Actualizar";
                botonActualizar.className = "btn btn-warning actualizar-libro";
                botonActualizar.onclick = function(e) {
                    $('#exampleModal').modal('show');
                    consultarLibroId(this.value);
                };

                var celdaOpcion2 = document.createElement("td");
                var botonEliminar = document.createElement("button");
                botonEliminar.innerHTML = "Eliminar";
                botonEliminar.className = "btn btn-danger eliminar-libro";
                botonEliminar.onclick = function() {
                    eliminarLibro(["id_libro"]);
                };

                trResgistro.appendChild(celdaId);
                trResgistro.appendChild(celdaTitulo);
                trResgistro.appendChild(celdaAutor);
                trResgistro.appendChild(celdaISBN);
                trResgistro.appendChild(celdaGenero);
                trResgistro.appendChild(celdaEjemplaresDisponibles);
                trResgistro.appendChild(EjemplaresOcupados);
                celdaOpcion.appendChild(botonActualizar);
                trResgistro.appendChild(celdaOpcion);
                celdaOpcion2.appendChild(botonEliminar);
                trResgistro.appendChild(celdaOpcion2);
                cuerpoTablaLibros.appendChild(trResgistro);
            }
        },
        error: function(error) {
            alert("Error en la petición " + error);
        }
    });
}

function consultarLibroId(id) {
    $.ajax({
        url: url + id,
        type: "GET",
        success: function(result) {
            document.getElementById("id_libro").value = result["id_libro"];
            document.getElementById("titulo").value = result["titulo"];
            document.getElementById("autor").value = result["autor"];
            document.getElementById("isbn").value = result["isbn"];
            document.getElementById("genero").value = result["genero"];
            document.getElementById("num_ejemplares_dispo").value = result["num_ejemplares_dispo"];
            document.getElementById("num_ejemplares_ocupados").value = result["num_ejemplares_ocupados"];
        },
        error: function(error) {
            alert("Error al consultar el libro con ID: " + id);
        }
    });
}

function actualizarLibro() {
    var id_libro = document.getElementById("id_libro").value;
    var formData = {
        "titulo": document.getElementById("titulo").value,
        "autor": document.getElementById("autor").value,
        "isbn": document.getElementById("isbn").value,
        "genero": document.getElementById("genero").value,
        "num_ejemplares_dispo": document.getElementById("num_ejemplares_dispo").value,
        "num_ejemplares_ocupados": document.getElementById("num_ejemplares_ocupados").value
    };

    if (validarCampos()) {
        $.ajax({
            url: url + id_libro,
            type: "PUT",
            data: formData,
            success: function(result) {
                Swal.fire({
                    title: "¡Excelente!",
                    text: "Se guardó correctamente",
                    icon: "success"
                });
                listarLibros();
                $('#exampleModal').modal('hide');
            },
            error: function(error) {
                Swal.fire({
                    title: "¡Error!",
                    text: "No se guardó",
                    icon: "error"
                });
            }
        });
    } else {
        Swal.fire({
            title: "¡Error!",
            text: "Llene todos los campos correctamente",
            icon: "error"
        });
    }
}

function eliminarLibro(id) {
    Swal.fire({
        title: '¿Está seguro?',
        text: "Esta acción no se puede deshacer",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Sí, eliminar!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: url + id,
                type: "DELETE",
                success: function(result) {
                    Swal.fire(
                        'Eliminado!',
                        'El registro ha sido eliminado.',
                        'success'
                    );
                    listarLibros();
                },
                error: function(error) {
                    Swal.fire(
                        'Error!',
                        'No se pudo eliminar el registro.',
                        'error'
                    );
                }
            });
        }
    });
}

function registrarLibro() {
    var formData = {
        "titulo": document.getElementById("titulo").value,
        "autor": document.getElementById("autor").value,
        "isbn": document.getElementById("isbn").value,
        "genero": document.getElementById("genero").value,
        "num_ejemplares_dispo": document.getElementById("num_ejemplares_dispo").value,
        "num_ejemplares_ocupados": document.getElementById("num_ejemplares_ocupados").value
    };

    if (validarCampos()) {
        $.ajax({
            url: url,
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(formData),
            success: function(result) {
                Swal.fire({
                    title: "¡Excelente!",
                    text: "Se guardó correctamente",
                    icon: "success"
                });
                $('#exampleModal').modal('hide');
            },
            error: function(error) {
                Swal.fire({
                    title: "¡Error!",
                    text: "No se guardó",
                    icon: "error"
                });
            }
        });
    } else {
        Swal.fire({
            title: "¡Error!",
            text: "Llene todos los campos correctamente",
            icon: "error"
        });
    }
}

function validarCampos() {
    var titulo = document.getElementById("titulo");
    var autor = document.getElementById("autor");
    var isbn = document.getElementById("isbn");
    var genero = document.getElementById("genero");
    var num_ejemplares_dispo = document.getElementById("num_ejemplares_dispo");
    var num_ejemplares_ocupados = document.getElementById("num_ejemplares_ocupados");

    return validarTitulo(titulo) && validarAutor(autor) && validarISBN(isbn) &&
        validarGenero(genero) && validarNumEjempDispo(num_ejemplares_dispo) &&
        validarNumEjempOcup(num_ejemplares_ocupados);
}
function validarTitulo(titulo) {
    var valor = titulo.value.trim();
    var valido = valor.length >= 3 && valor.length <= 50;

    actualizarClaseValidacion(titulo, valido);
    return valido;
}
function validarAutor(autor) {
    var valor = autor.value.trim();
    var valido = valor.length >= 5 && valor.length <= 100;

    actualizarClaseValidacion(autor, valido);
    return valido;
}
function validarISBN(isbn) {
    var valor = isbn.value.trim();
    var valido = valor.length === 13;

    actualizarClaseValidacion(isbn, valido);
    return valido;
}
function validarGenero(genero) {
    var valor = genero.value.trim();
    var valido = valor.length >= 4 && valor.length <= 25;

    actualizarClaseValidacion(genero, valido);
    return valido;
}
function validarNumEjempDispo(num_ejemplares_dispo) {
    var valor = num_ejemplares_dispo.value.trim();
    var valido = valor.length > 0 && parseInt(valor) >= 0 && parseInt(valor) <= 3000;

    actualizarClaseValidacion(num_ejemplares_dispo, valido);
    return valido;
}
function validarNumEjempOcup(num_ejemplares_ocupados) {
    var valor = num_ejemplares_ocupados.value.trim();
    var valido = valor.length > 0 && parseInt(valor) >= 0 && parseInt(valor) <= 3000;

    actualizarClaseValidacion(num_ejemplares_ocupados, valido);
    return valido;
}
function actualizarClaseValidacion(elemento, valido) {
    if (valido) {
        elemento.classList.remove("is-invalid");
        elemento.classList.add("is-valid");
    } else {
        elemento.classList.remove("is-valid");
        elemento.classList.add("is-invalid");
    }
}

function limpiar() {
    document.getElementById("titulo").value = "";
    document.getElementById("autor").value = "";
    document.getElementById("isbn").value = "";
    document.getElementById("genero").value = "";
    document.getElementById("num_ejemplares_dispo").value = "";
    document.getElementById("num_ejemplares_ocupados").value = "";

    document.getElementById("titulo").classList.remove("is-valid", "is-invalid");
    document.getElementById("autor").classList.remove("is-valid", "is-invalid");
    document.getElementById("isbn").classList.remove("is-valid", "is-invalid");
    document.getElementById("genero").classList.remove("is-valid", "is-invalid");
    document.getElementById("num_ejemplares_dispo").classList.remove("is-valid", "is-invalid");
    document.getElementById("num_ejemplares_ocupados").classList.remove("is-valid", "is-invalid");
}





