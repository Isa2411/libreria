package com.example.libreriamenu

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.libreriamenu.config.config
import org.json.JSONObject
import java.lang.Exception


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GuardarLibroFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GuardarLibroFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //se definen las variables del formulario
    private lateinit var txtTitulo: EditText
    private lateinit var txtAutor: EditText
    private lateinit var txtISBN: EditText
    private lateinit var txtGenero: EditText
    private lateinit var txtDisponible: EditText
    private lateinit var txtOcupado: EditText
    private lateinit var btnGuardar: Button
    private var id:String = ""
    /*
    Request es peticion que se hace a la API
    sgtringrequest=responde un String
    JsonRequest= responde un json
    JsonArrayrequest= responde un arreglo de json
     */




    fun guardarLibro() {
        //Manejo de excepciones
        try {
            if (id == "") {//se crea el libro
                //se crea la peticion
                /* val request = object : StringRequest(
                    Request.Method.POST, //metodo de la peticion
                    config.urllibro,//url de la peticion
                    Response.Listener {
                        //metodo que se ejecuta cuando la peticion es correcta
                        Toast.makeText(
                            context,
                            "Se guardó correctamente",
                            Toast.LENGTH_LONG
                        ).show()
                    },
                    Response.ErrorListener {
                        //metodo que se ejcuta cuando la peticion genera error
                        Toast.makeText(
                            context,
                            "Error al guardar",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                ) {
                    //Se agregan los datos para la peticion
                    override fun getParams(): Map<String, String> {
                        var parametros = HashMap<String, String>()
                        parametros.put("Titulo", txtTitulo.text.toString())
                        parametros.put("Autor", txtAutor.text.toString())
                        parametros.put("ISBN", txtISBN.text.toString())
                        parametros.put("Genero", txtGenero.text.toString())
                        parametros.put("Disponibles", txtDisponible.text.toString())
                        parametros.put("Ocupado", txtOcupado.text.toString())
                        //uno por cada dato que se requiere
                        return parametros
                    }
                }
                */
                var parametros= JSONObject()
                parametros.put("titulo", txtTitulo.text.toString())
                parametros.put("autor", txtAutor.text.toString())
                parametros.put("isbn", txtISBN.text.toString())
                parametros.put("genero", txtGenero.text.toString())
                parametros.put("num_ejemplares_disponibles", txtDisponible.text.toString())
                parametros.put("num_ejemplares_ocupados", txtOcupado.text.toString())
                //uno por cada dato que se requiere

                var request= JsonObjectRequest(
                    Request.Method.POST,//metodo
                    config.urlLibro,//url
                    parametros,//datos de la peticion
                    {response->
                        Toast.makeText(
                            context,
                            "Se guardó correctamente",
                            Toast.LENGTH_LONG
                        ).show()
                    },//cuando la repsuesta es correcta
                    {error->
                        Toast.makeText(
                            context,
                            "Se generó un error",
                            Toast.LENGTH_LONG
                        ).show()
                    }//cuando es incorrecta
                )

                //Se crea la cola de trabajo y se añade la peticion
                var queue = Volley.newRequestQueue(context)
                //se añade la peticion
                queue.add(request)

            } else {//se actualiza el libro

            }
        } catch (error: Exception) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_guardar_libro, container, false)
        txtTitulo = view.findViewById(R.id.txtTitulo)
        txtAutor = view.findViewById(R.id.txtAutor)
        txtISBN = view.findViewById(R.id.txtISBN)
        txtGenero = view.findViewById(R.id.txtGenero)
        txtDisponible = view.findViewById(R.id.txtDisponibles)
        txtOcupado = view.findViewById(R.id.txtOcupados)
        btnGuardar = view.findViewById(R.id.btnGuardar)
        btnGuardar.setOnClickListener() {
            guardarLibro()
        }
        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GuardarLibroFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GuardarLibroFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}