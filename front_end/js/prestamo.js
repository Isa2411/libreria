var url = "http://localhost:8080/api/libreria/prestamo/";

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

function listarPrestamos() {
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

            var cuerpoTablaPrestamos = document.getElementById("cuerpoTablaPrestamos");
            cuerpoTablaPrestamos.innerHTML = "";
            for (var i = 0; i < result.length; i++) {
                var trResgistro = document.createElement("tr");

                var celdaId = document.createElement("td");
                celdaId.innerText = result[i]["id_prestamo"];

                var celdaFecha_prestamo = document.createElement("td");
                celdaFecha_prestamo.innerText = result[i]["fecha_prestamo"];

                var celdaFecha_devo = document.createElement("td");
                celdaFecha_devo.innerText = result[i]["fecha_devo"];

                var celdaUsua_prestamo = document.createElement("td");
                celdaUsua_prestamo.innerText = result[i]["usua_prestamo"];

                var celdaLibro_prestado = document.createElement("td");
                celdaLibro_prestado.innerText = result[i]["libro_prestado"];

                var celdaEstado_prestamo = document.createElement("td");
                celdaEstado_prestamo.innerText = result[i]["Estado_prestamo"];

                var celdaOpcion = document.createElement("td");
                var botonActualizar = document.createElement("button");
                botonActualizar.value = result[i]["id_prestamo"];
                botonActualizar.innerHTML = "Actualizar";
                botonActualizar.className = "btn btn-warning actualizar-prestamo";
                botonActualizar.onclick = function(e) {
                    $('#exampleModal').modal('show');
                    consultarPrestmoId(this.value);
                };

                var celdaOpcion2 = document.createElement("td");
                var botonEliminar = document.createElement("button");
                botonEliminar.innerHTML = "Eliminar";
                botonEliminar.className = "btn btn-danger eliminar-prestamo";
                botonEliminar.onclick = function() {
                    eliminarPrestmo(["id_prestamo"]);
                };

                trResgistro.appendChild(celdaId);
                trResgistro.appendChild(celdaFecha_prestamo);
                trResgistro.appendChild(celdaFecha_devo);
                trResgistro.appendChild(celdaUsua_prestamo);
                trResgistro.appendChild(celdaLibro_prestado);
                trResgistro.appendChild(celdaEstado_prestamo);
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

function consultarPrestamoId(id) {
    $.ajax({
        url: url + id,
        type: "GET",
        success: function(result) {
            document.getElementById("id_prestamo").value = result["id_prestamo"];
            document.getElementById("fecha_prestamo").value = result["fecha_prestamo"];
            document.getElementById("fecha_devo").value = result["fecha_devo"];
            document.getElementById("usua_prestamo").value = result["usua_prestamo"];
            document.getElementById("libro_prestado").value = result["libro_prestado"];
            document.getElementById("Estado_prestamo").value = result["Estado_prestamo"];
        },
        error: function(error) {
            alert("Error al consultar el prestamo con ID: " + id);
        }
    });
}

function actualizarPrestamo() {
    var id_libro = document.getElementById("id_prestamo").value;
    var formData = {
        "fecha_prestamo": document.getElementById("fecha_prestamo").value,
        "fecha_devo": document.getElementById("fecha_devo").value,
        "usua_prestamo": document.getElementById("usua_prestamo").value,
        "libro_prestado": document.getElementById("libro_prestado").value,
        "Estado_prestamo": document.getElementById("Estado_prestamo").value
    };

    if (validarCampos()) {
        $.ajax({
            url: url + id_prestamo,
            type: "PUT",
            data: formData,
            success: function(result) {
                Swal.fire({
                    title: "¡Excelente!",
                    text: "Se guardó correctamente",
                    icon: "success"
                });
                listarPrestamos();
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

function eliminarPrestamo(id) {
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

function registrarPrestamo() {
    var formData = {
        "fecha_prestamo": document.getElementById("fecha_prestamo").value,
        "fecha_devo": document.getElementById("fecha_devo").value,
        "usua_prestamo": document.getElementById("usua_prestamo").value,
        "libro_prestado": document.getElementById("libro_prestado").value,
        "Estado_prestamo": document.getElementById("Estado_prestamo").value
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
    var fecha_prestamo = document.getElementById("fecha_prestamo");
    var fecha_devo = document.getElementById("fecha_devo");
    var usua_prestamo = document.getElementById("usua_prestamo");
    var genelibro_prestadoro = document.getElementById("libro_prestado");
    var estado_prestamo = document.getElementById("Estado_prestamo");

    return validarFecha_prestamo(fecha_prestamo) && validarFecha_devo(fecha_devo) && validarUsua_prestamo(usua_prestamo) &&
        validarGenelibro_prestadoro(genelibro_prestadoro) && validarEstado_prestamo(estado_prestamo);
}
function validarFecha_prestamo(fecha_prestamo) {
    var valor = fecha_prestamo.value.trim();
    var valido = valor.length >= 3 && valor.length <= 50;

    actualizarClaseValidacion(fecha_prestamo, valido);
    return valido;
}
function validarFecha_devo(fecha_devo) {
    var valor = fecha_devo.value.trim();
    var valido = valor.length >= 5 && valor.length <= 100;

    actualizarClaseValidacion(fecha_devo, valido);
    return valido;
}
function validarUsua_prestamo(usua_prestamo) {
    var valor = usua_prestamo.value.trim();
    var valido = valor.length === 13;

    actualizarClaseValidacion(usua_prestamo, valido);
    return valido;
}
function validarGenelibro_prestadoro(genelibro_prestadoro) {
    var valor = genelibro_prestadoro.value.trim();
    var valido = valor.length >= 4 && valor.length <= 25;

    actualizarClaseValidacion(genelibro_prestadoro, valido);
    return valido;
}
function validarEstado_prestamo(estado_prestamo) {
    var valor = estado_prestamo.value.trim();
    var valido = valor.length > 0 && parseInt(valor) >= 0 && parseInt(valor) <= 3000;

    actualizarClaseValidacion(estado_prestamo, valido);
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
    document.getElementById("fecha_prestamo").value = "";
    document.getElementById("fecha_devo").value = "";
    document.getElementById("usua_prestamo").value = "";
    document.getElementById("genelibro_prestadoro").value = "";
    document.getElementById("estado_prestamo").value = "";

    document.getElementById("fecha_prestamo").classList.remove("is-valid", "is-invalid");
    document.getElementById("fecha_devo").classList.remove("is-valid", "is-invalid");
    document.getElementById("usua_prestamo").classList.remove("is-valid", "is-invalid");
    document.getElementById("genelibro_prestadoro").classList.remove("is-valid", "is-invalid");
    document.getElementById("estado_prestamo").classList.remove("is-valid", "is-invalid");
}





