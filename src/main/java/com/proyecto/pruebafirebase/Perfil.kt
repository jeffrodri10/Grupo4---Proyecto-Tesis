package com.proyecto.pruebafirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import com.google.firebase.firestore.FirebaseFirestore

class Perfil : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)
        val bundle:Bundle?=intent.extras
        val email:String?=bundle?.getString("email")
        setup(email?:"")
    }
    private fun setup(email:String){
        var Nombre = findViewById<View>(R.id.nombreperfil) as TextView
        var Apellido = findViewById<View>(R.id.apellidoperfil) as TextView
        var Dni = findViewById<View>(R.id.dniperfil) as TextView
        var Correo = findViewById<View>(R.id.correoperfil) as TextView
        var Telefono = findViewById<View>(R.id.celularperfil) as TextView
        var Zona = findViewById<View>(R.id.zonaperfil) as TextView
        var Ciudad = findViewById<View>(R.id.ciudadperfil) as TextView
        var Calle = findViewById<View>(R.id.calleperfil) as TextView
        var Servicio = findViewById<View>(R.id.servicioperfil) as TextView
        var Estado = findViewById<View>(R.id.estadoperfil) as TextView
        var foto = findViewById<LottieAnimationView>(R.id.foto) as LottieAnimationView
        var like =false
        foto.setOnClickListener{
            like = pressanimation1(foto,R.raw.perfil,like)
        }
        Correo.text=email
         db.collection("Persona").document(email).get().addOnSuccessListener {
             Nombre.setText(it.get("Nombre") as String?);
             Apellido.setText(it.get("Apellido") as String?);
             Dni.setText(it.get("Dni") as String?);
             Telefono.setText(it.get("Telefono") as String?);
             Ciudad.setText(it.get("Ciudad") as String?);
             Zona.setText(it.get("Zona") as String?);
             Calle.setText(it.get("Calle") as String?);
             Servicio.setText(it.get("Servicio") as String?);
             Estado.setText(it.get("Estado") as String?)
         }

        var editar = findViewById<Button>(R.id.btnEditarperfil) as Button
        var cerrarsesion = findViewById<Button>(R.id.btnlogout) as Button

        editar.setOnClickListener() {
            val homeIntent= Intent(this,EditPerfil::class.java).apply {
                putExtra("email",email)
            }
            startActivity(homeIntent)
        }
        cerrarsesion.setOnClickListener() {
            val homeIntent= Intent(this, HomeActivity::class.java).apply {
                putExtra("email",email)
            }
            startActivity(homeIntent)
            finish()
        }
    }
    private fun pressanimation1(imageView: LottieAnimationView,animation: Int,like:Boolean):Boolean{
        if (!like){
            imageView.setAnimation(animation)
            imageView.repeatCount=5
            imageView.playAnimation()
        }else{
            imageView.setImageResource(R.mipmap.profilelogo)
        }
        return !like
    }
}
