package com.proyecto.pruebafirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(2000)
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val analytics:FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle= Bundle()
        bundle.putString("mensaje","Integracion de FireBase Completa")
        analytics.logEvent("InitSceen",bundle)
        //setup
        setup()
    }

    private fun setup(){
        title="Autenticación"
        var Iniciar = findViewById<Button>(R.id.btnIniciar) as Button
        var registro = findViewById<Button>(R.id.btnregistro) as Button
        var email = findViewById<View>(R.id.txtusu) as TextView
        var contra = findViewById<View>(R.id.txtcontra) as TextView
        var vista = findViewById<Button>(R.id.btnVista) as Button
        registro.setOnClickListener{
            val i = Intent(this,RegistroUsu ::class.java)
            this.startActivity(i)
        }
        vista.setOnClickListener{
            val i = Intent(this,Inicio ::class.java)
            this.startActivity(i)
        }
        Iniciar.setOnClickListener {
            val correolog=email.text.toString().trim()
            val contraseñalog=contra.text.toString().trim()
            if (correolog.isEmpty()){
                email.error="Ingrese su Correo"
                email.requestFocus()
                return@setOnClickListener
            }
            if (contraseñalog.isEmpty()){
                contra.error="Ingrese su Contraseña"
                contra.requestFocus()
                return@setOnClickListener
            }
            if (email.text.isNotEmpty() && contra.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email.text.toString(),contra.text.toString()).addOnCompleteListener{
                    if (it.isSuccessful){
                        showHome(it.result?.user?.email?:"",ProviderType.BASIC)
                    }else{
                        showAlert()
                    }
                }
            }
        }
    }
    private fun showAlert(){
        val builder=AlertDialog.Builder(this)
        builder.setTitle("Se ha producido un error de autenticación")
        builder.setMessage("Usuario o Contraseña incorrectos intentalo de nuevo")
        builder.setPositiveButton("Aceptar",null,)
        val dialog:AlertDialog=builder.create()
        dialog.show()
    }
    private fun showHome(email:String,provider:ProviderType){
        val homeIntent= Intent(this,Interfaz::class.java).apply {
            putExtra("email",email)
            putExtra("provider",provider.name)
        }
        startActivity(homeIntent)
        finish()
    }
}