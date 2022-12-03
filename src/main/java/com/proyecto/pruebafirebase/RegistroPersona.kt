package com.proyecto.pruebafirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegistroPersona : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_persona)
        val bundle:Bundle?=intent.extras
        val email:String?=bundle?.getString("email")
        val provider:String?=bundle?.getString("provider")
        setup(email?:"",provider?:"")
    }
    private fun setup(email: String,provider: String){
        title="Registar perfil"
        var agregar = findViewById<Button>(R.id.btnRegistroDatos) as Button
        var emailhome = findViewById<View>(R.id.txtcorreoperfil) as TextView
        var Nombre = findViewById<View>(R.id.txtNombreDatos) as TextView
        var Apellido = findViewById<View>(R.id.txtApellidoDatos) as TextView
        var Dni = findViewById<View>(R.id.txtDniDatos) as TextView
        var Telefono = findViewById<View>(R.id.txtTelefonoDatos) as TextView
        var Ciudad = findViewById<View>(R.id.txtCiudadDatos) as TextView
        var Direccion = findViewById<View>(R.id.txtDireccionDatos) as TextView
        var Zona = findViewById<View>(R.id.txtZonaDatos) as TextView
        var Servicio = findViewById<View>(R.id.txtServicioDatos) as TextView
        var Estado = findViewById<View>(R.id.txtEstadoDatos) as TextView
        emailhome.text=email
        agregar.setOnClickListener {
            val nombre = Nombre.text.toString().trim()
            val apellido= Apellido.text.toString().trim()
            val dni= Dni.text.toString().trim()
            val telefono= Telefono.text.toString().trim()
            val ciudad = Ciudad.text.toString().trim()
            val direcciom = Direccion.text.toString().trim()
            val zona = Zona.text.toString().trim()
            val servicio = Servicio.text.toString().trim()
            val estado = Estado.text.toString().trim()

            if (nombre.isEmpty()){
                Nombre.error="Ingrese su Nombre"
                Nombre.requestFocus()
                return@setOnClickListener
            }
            if (apellido.isEmpty()){
                Apellido.error="Ingrese su Apellido"
                Apellido.requestFocus()
                return@setOnClickListener
            }
            if (dni.isEmpty()){
                Dni.error="Ingrese su Dni"
                Dni.requestFocus()
                return@setOnClickListener
            }
            if (telefono.isEmpty()){
                Telefono.error="Ingrese su numero de celular"
                Telefono.requestFocus()
                return@setOnClickListener
            }
            if (direcciom.isEmpty()){
                Direccion.error="Ingrese su Calle"
                Direccion.requestFocus()
                return@setOnClickListener
            }
            if (ciudad.isEmpty()){
                Ciudad.error="Ingrese su Ciudad"
                Ciudad.requestFocus()
                return@setOnClickListener
            }
            if (zona.isEmpty()){
                Zona.error="Ingrese el nombre de su Zona"
                Zona.requestFocus()
                return@setOnClickListener
            }
            if (servicio.isEmpty()){
                Servicio.error="Ingrese su codigo de Servicio"
                Servicio.requestFocus()
                return@setOnClickListener
            }
            if (estado.isEmpty()){
                Estado.error="Ingrese su Estado de Titulacion"
                Estado.requestFocus()
                return@setOnClickListener
            }

            db.collection("Persona").document(email).set(
                hashMapOf(
                    "Nombre" to Nombre.text.toString(),
                    "Apellido" to Apellido.text.toString(),
                    "Dni" to Dni.text.toString(),
                    "Telefono" to Telefono.text.toString(),
                    "Ciudad" to Ciudad.text.toString(),
                    "Calle" to Direccion.text.toString(),
                    "Zona" to Zona.text.toString(),
                    "Servicio" to Servicio.text.toString(),
                    "Estado" to Estado.text.toString())
            )

            val url = "http://192.168.1.5/ffcf/crearpersona.php"
            val queue = Volley.newRequestQueue(this)
            var resultadoPost = object : StringRequest(Request.Method.POST, url,
                Response.Listener<String> { response ->
                    Toast.makeText(this, "BUENA , AHORA YA ESTAS REGISTRADO", Toast.LENGTH_LONG).show()
                }, Response.ErrorListener { error ->
                    Toast.makeText(this, "ERROR DE REGISTRO $error", Toast.LENGTH_LONG)
                }) {
                override fun getParams(): MutableMap<String, String>? {
                    val parametros=HashMap<String,String>()
                    parametros.put("NOMBRE",Nombre?.text.toString())
                    parametros.put("APELLIDO",Apellido?.text.toString())
                    parametros.put("DNI",Dni?.text.toString())
                    parametros.put("CELULAR",Telefono?.text.toString())
                    parametros.put("CORREO",emailhome?.text.toString())
                    parametros.put("CALLE",Direccion?.text.toString())
                    parametros.put("CIUDAD",Ciudad?.text.toString())
                    parametros.put("ZONA",Zona?.text.toString())
                    parametros.put("SERVICIO",Servicio?.text.toString())
                    parametros.put("ESTADO",Estado?.text.toString())

                    return parametros
                }
            }
            queue.add(resultadoPost)
            val homeIntent= Intent(this,Interfaz::class.java).apply {
                putExtra("email",email)
            }
            startActivity(homeIntent)
        }
    }
}