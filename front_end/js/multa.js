var url = "http://localhost:8080/api/libreria/multa/";

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

function listarMultas() {
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

            var cuerpoTablaMultas = document.getElementById("cuerpoTablaMultas");
            cuerpoTablaMultas.innerHTML = "";
            for (var i = 0; i < result.length; i++) {
                var trResgistro = document.createElement("tr");

                var celdaId = document.createElement("td");
                celdaId.innerText = result[i]["id_multa"];

                var celdaUsua_multado = document.createElement("td");
                celdaUsua_multado.innerText = result[i]["usua_multado"];

                var celdaAutor = document.createElement("td");
                celdaAutor.innerText = result[i]["valor_multa"];

                var celdaValor_multa = document.createElement("td");
                celdaValor_multa.innerText = result[i]["fecha_multa"];

                var celdaEstado_multa = document.createElement("td");
                celdaEstado_multa.innerText = result[i]["estado_multa"];

                var celdaOpcion = document.createElement("td");
                var botonActualizar = document.createElement("button");
                botonActualizar.value = result[i]["id_multa"];
                botonActualizar.innerHTML = "Actualizar";
                botonActualizar.className = "btn btn-warning actualizar-multa";
                botonActualizar.onclick = function(e) {
                    $('#exampleModal').modal('show');
                    consultarMultaId(this.value);
                };

                var celdaOpcion2 = document.createElement("td");
                var botonEliminar = document.createElement("button");
                botonEliminar.innerHTML = "Eliminar";
                botonEliminar.className = "btn btn-danger eliminar-multa";
                botonEliminar.onclick = function() {
                    eliminarMulta(["id_multa"]);
                };

                trResgistro.appendChild(celdaId);
                trResgistro.appendChild(celdaUsua_multado);
                trResgistro.appendChild(celdaValor_multa);
                trResgistro.appendChild(celdaFecha_multa);
                trResgistro.appendChild(celdaEstado_multa);
                celdaOpcion.appendChild(botonActualizar);
                trResgistro.appendChild(celdaOpcion);
                celdaOpcion2.appendChild(botonEliminar);
                trResgistro.appendChild(celdaOpcion2);
                cuerpoTablaMultas.appendChild(trResgistro);
            }
        },
        error: function(error) {
            alert("Error en la petición " + error);
        }
    });
}

function consultarMultaId(id) {
    $.ajax({
        url: url + id,
        type: "GET",
        success: function(result) {
            document.getElementById("id_multa").value = result["id_multa"];
            document.getElementById("usua_multado").value = result["usua_multado"];
            document.getElementById("valor_multa").value = result["valor_multa"];
            document.getElementById("fecha_multa").value = result["fecha_multa"];
            document.getElementById("estado_multa").value = result["estado_multa"];
        },
        error: function(error) {
            alert("Error al consultar el usuario con ID: " + id);
        }
    });
}

function actualizarMulta() {
    var id_multa = document.getElementById("id_multa").value;
    var formData = {
        "usua_multado": document.getElementById("usua_multado").value,
        "valor_multa": document.getElementById("valor_multa").value,
        "fecha_multa": document.getElementById("fecha_multa").value,
        "estado_multa": document.getElementById("estado_multa").value
    };

    if (validarCampos()) {
        $.ajax({
            url: url + id_multa,
            type: "PUT",
            data: formData,
            success: function(result) {
                Swal.fire({
                    title: "¡Excelente!",
                    text: "Se guardó correctamente",
                    icon: "success"
                });
                listarMultas();
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

function eliminarMulta(id) {
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
                    listarMultas();
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

function registrarMulta() {
    var formData = {
        "usua_multado": document.getElementById("usua_multado").value,
        "valor_multa": document.getElementById("valor_multa").value,
        "fecha_multa": document.getElementById("fecha_multa").value,
        "estado_multa": document.getElementById("estado_multa").value
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
    var usua_multado = document.getElementById("usua_multado");
    var valor_multa = document.getElementById("valor_multa");
    var fecha_multa = document.getElementById("fecha_multa");
    var estado_multa = document.getElementById("estado_multa")

    return validarUsua_multado(usua_multado) && validarValor_multa(valor_multa) && validarFecha_multa(fecha_multa) &&
        validarEstado_multa(estado_multa);
}
function validarUsua_multado(usua_multado) {
    var valor = usua_multado.value.trim();
    var valido = valor.length >= 3 && valor.length <= 50;

    actualizarClaseValidacion(usua_multado, valido);
    return valido;
}
function validarValor_multa(valor_multa) {
    var valor = valor_multa.value.trim();
    var valido = valor.length >= 5 && valor.length <= 100;

    actualizarClaseValidacion(valor_multa, valido);
    return valido;
}
function validarFecha_multa(fecha_multa) {
    var valor = fecha_multa.value.trim();
    var valido = valor.length === 13;

    actualizarClaseValidacion(fecha_multa, valido);
    return valido;
}
function validarEstado_multa(estado_multa) {
    var valor = estado_multa.value.trim();
    var valido = valor.length >= 4 && valor.length <= 25;

    actualizarClaseValidacion(estado_multa, valido);
    return valido;
}

function limpiar() {
    document.getElementById("usua_multado").value = "";
    document.getElementById("valor_multa").value = "";
    document.getElementById("fecha_multa").value = "";
    document.getElementById("estado_multa").value = "";

    document.getElementById("usua_multado").classList.remove("is-valid", "is-invalid");
    document.getElementById("valor_multa").classList.remove("is-valid", "is-invalid");
    document.getElementById("fecha_multa").classList.remove("is-valid", "is-invalid");
    document.getElementById("estado_multa").classList.remove("is-valid", "is-invalid");
}





