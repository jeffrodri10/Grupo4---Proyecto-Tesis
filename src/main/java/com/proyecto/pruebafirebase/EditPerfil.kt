package com.proyecto.pruebafirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.firestore.FirebaseFirestore

class EditPerfil : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_perfil)
        val bundle:Bundle?=intent.extras
        val email:String?=bundle?.getString("email")
        setup(email?:"")
    }
    private fun setup(email: String){
        title="Registar perfil"
        var Guardar = findViewById<Button>(R.id.btnguardaredit) as Button
        var correo = findViewById<View>(R.id.txtcorreoedit) as TextView
        var Nombre = findViewById<View>(R.id.txtnombreedit) as TextView
        var Apellido = findViewById<View>(R.id.txtapellidoedit) as TextView
        var Dni = findViewById<View>(R.id.txtdniedit) as TextView
        var Telefono = findViewById<View>(R.id.txtcelularedit) as TextView
        var Ciudad = findViewById<View>(R.id.txtciudadedit) as TextView
        var Zona = findViewById<View>(R.id.txtzonaedit) as TextView
        var Calle = findViewById<View>(R.id.txtcalleedit) as TextView
        var Servicio = findViewById<View>(R.id.txtservicioedit) as TextView
        var Estado = findViewById<View>(R.id.txtestadoedit) as TextView


        correo.text=email
        db.collection("Persona").document(email).get().addOnSuccessListener {
            Nombre.setText(it.get("Nombre") as String?);
            Apellido.setText(it.get("Apellido") as String?);
            Dni.setText(it.get("Dni") as String?);
            Telefono.setText(it.get("Telefono") as String?);
            Ciudad.setText(it.get("Ciudad") as String?);
            Zona.setText(it.get("Zona") as String?)
            Calle.setText(it.get("Calle") as String?);
            Servicio.setText(it.get("Servicio") as String?);
            Estado.setText(it.get("Estado") as String?)
        }
        Guardar.setOnClickListener {
            db.collection("Persona").document(email).set(
                hashMapOf(
                    "Nombre" to Nombre.text.toString(),
                    "Apellido" to Apellido.text.toString(),
                    "Telefono" to Telefono.text.toString(),
                    "Dni" to Dni.text.toString(),
                    "Ciudad" to Ciudad.text.toString(),
                    "Zona" to Zona.text.toString(),
                    "Calle" to Calle.text.toString(),
                    "Servicio" to Servicio.text.toString(),
                    "Estado" to Estado.text.toString())
            )
            val homeIntent= Intent(this,Perfil::class.java).apply {
                putExtra("email",email)
            }
            startActivity(homeIntent)
            val NOMBRE = Nombre.text.toString()
            val APELLIDO = Apellido.text.toString()
            val CELULAR = Telefono.text.toString()
            val DNI = Dni.text.toString()
            val CORREO = correo.text.toString()
            val CIUDAD = Ciudad.text.toString()
            val ZONA = Zona.text.toString()
            val CALLE = Calle.text.toString()
            val SERVICIO = Servicio.text.toString()
            val ESTADO = Estado.text.toString()
            val url = "http://192.168.1.5/ffcf/editarpersona.php?CORREO=$CORREO&NOMBRE=$NOMBRE&APELLIDO=$APELLIDO&DNI=$DNI&CELULAR=$CELULAR&CIUDAD=$CIUDAD&ZONA=$ZONA&CALLE=$CALLE&SERVICIO=$SERVICIO&ESTADO=$ESTADO"
            val queue = Volley.newRequestQueue(this)
            var resultadoPost = object : StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->
                    Toast.makeText(this, "SU USUARIO SE ACTUALIZO CORRECTAMENTE", Toast.LENGTH_LONG).show()
                }, Response.ErrorListener { error ->
                    Toast.makeText(this, " ERROR AL ACTUALIZAR USUARIO", Toast.LENGTH_LONG)
                }) {
                override fun getParams(): MutableMap<String, String>? {
                    val parametros=HashMap<String,String>()
                    parametros.put("CORREO", CORREO!!)
                    parametros.put("NOMBRE", NOMBRE!!)
                    parametros.put("APELLIDO",APELLIDO!!)
                    parametros.put("DNI",DNI!!)
                    parametros.put("CELULAR",CELULAR!!)
                    parametros.put("CIUDAD",CIUDAD!!)
                    parametros.put("ZONA",ZONA!!)
                    parametros.put("CALLE",CALLE!!)
                    parametros.put("SERVICIO",SERVICIO!!)
                    parametros.put("ESTADO",ESTADO!!)
                    return parametros
                }
            }
            queue.add(resultadoPost)
        }
    }
}