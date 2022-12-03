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

class RegistroUsu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usu)
        setup()
    }
    private fun setup(){
        title="Registrar Cuenta"
        var registro = findViewById<Button>(R.id.btnregistrousu) as Button
        var email = findViewById<View>(R.id.txtcorreoregi) as TextView
        var contra = findViewById<View>(R.id.txtcontraregistro) as TextView
        registro.setOnClickListener{
            val correolog=email.text.toString().trim()
            val contraseñalog=contra.text.toString().trim()
            if (correolog.isEmpty()){
                email.error="Ingrese un Correo"
                email.requestFocus()
                return@setOnClickListener
            }
            if (contraseñalog.isEmpty()){
                contra.error="Ingrese una Contraseña"
                contra.requestFocus()
                return@setOnClickListener
            }
            if (email.text.toString().isNotEmpty() && contra.text.toString().isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.text.toString(),contra.text.toString()).addOnCompleteListener{
                    if (it.isSuccessful){
                        showHome(it.result?.user?.email?:"",ProviderType.BASIC)
                    }else{
                        showAlert()
                    }
                }
                val url = "http://192.168.1.5/ffcf/crearusuario.php"
                val queue = Volley.newRequestQueue(this)
                var resultadoPost = object : StringRequest(
                    Request.Method.POST, url,
                    Response.Listener<String> { response ->
                        Toast.makeText(this, "Usuario Insertado Exitosamente", Toast.LENGTH_LONG).show()
                    }, Response.ErrorListener { error ->
                        Toast.makeText(this, "Error $error", Toast.LENGTH_LONG)
                    }) {
                    override fun getParams(): MutableMap<String, String>? {
                        val parametros=HashMap<String,String>()
                        parametros.put("CORREO",email?.text.toString())
                        parametros.put("CONTRASEÑA",contra?.text.toString())

                        return parametros
                    }
                }
                queue.add(resultadoPost)
            }
        }
    }
    private fun showAlert(){
        val builder= AlertDialog.Builder(this)
        builder.setTitle("Se ha producido un error al crear su Cuenta")
        builder.setMessage("Usuario o Contraseña no válidos intentalo de nuevo")
        builder.setPositiveButton("Aceptar",null,)
        val dialog: AlertDialog =builder.create()
        dialog.show()
    }
    private fun showHome(email:String,provider:ProviderType){
        val homeIntent= Intent(this,RegistroPersona::class.java).apply {
            putExtra("email",email)
            putExtra("provider",provider.name)
        }
        startActivity(homeIntent)
    }
}