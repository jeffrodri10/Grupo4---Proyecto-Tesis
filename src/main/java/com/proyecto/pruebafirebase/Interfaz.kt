package com.proyecto.pruebafirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView

class Interfaz : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interfaz)
        val bundle:Bundle?=intent.extras
        val email:String?=bundle?.getString("email")
        setup(email?:"")
    }
    private fun setup(email:String) {
        var inicio = findViewById<Button>(R.id.interfazinicio) as Button
        var mensaje = findViewById<Button>(R.id.interfazmensaje) as Button
        var lugares = findViewById<Button>(R.id.interfazlugares) as Button
        var perfil = findViewById<Button>(R.id.interfazperfil) as Button


        inicio.setOnClickListener() {
            val i = Intent(this, Inicio::class.java)
            this.startActivity(i)
        }
        mensaje.setOnClickListener() {
            val i = Intent(this, Mensaje::class.java)
            this.startActivity(i)
        }
        lugares.setOnClickListener() {
            val i = Intent(this, Lugares::class.java)
            this.startActivity(i)
        }
        perfil.setOnClickListener() {
            val homeIntent= Intent(this,Perfil::class.java).apply {
                putExtra("email",email)
            }
            startActivity(homeIntent)
            finish()
        }
    }
}