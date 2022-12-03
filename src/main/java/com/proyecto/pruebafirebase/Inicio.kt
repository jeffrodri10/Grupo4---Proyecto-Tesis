package com.proyecto.pruebafirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.airbnb.lottie.LottieAnimationView

class Inicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        var chat = findViewById<LottieAnimationView>(R.id.chatbot) as LottieAnimationView
        var registrar = findViewById<LottieAnimationView>(R.id.registro) as LottieAnimationView
        var reporte = findViewById<LottieAnimationView>(R.id.powerbi) as LottieAnimationView
        var saludo = findViewById<LottieAnimationView>(R.id.saludoss) as LottieAnimationView
        var like =false
        chat.setOnClickListener{
            like = pressanimation1(chat,R.raw.chatbot,like)
        }
        registrar.setOnClickListener{
            like = pressanimation2(registrar,R.raw.registro,like)
        }
        reporte.setOnClickListener{
           like = pressanimation3(reporte,R.raw.grafico,like)
        }
        saludo.setOnClickListener{
           like = pressanimation4(saludo,R.raw.saludando,like)
        }
    }
    private fun pressanimation1(imageView: LottieAnimationView,animation: Int,like:Boolean):Boolean{
        if (!like){
            imageView.setAnimation(animation)
            imageView.repeatCount=5
            imageView.playAnimation()
        }else{
            imageView.setImageResource(R.mipmap.perfilbot)
        }
        return !like
    }
    private fun pressanimation2(imageView: LottieAnimationView,animation: Int,like:Boolean):Boolean{
        if (!like){
            imageView.setAnimation(animation)
            imageView.repeatCount=5
            imageView.playAnimation()
        }else{
            imageView.setImageResource(R.mipmap.logoregistro)
        }
        return !like
    }
    private fun pressanimation3(imageView: LottieAnimationView,animation: Int,like:Boolean):Boolean{
        if (!like){
            imageView.setAnimation(animation)
            imageView.repeatCount=5
            imageView.playAnimation()
        }else{
            imageView.setImageResource(R.mipmap.powerbilogo)
        }
        return !like
    }
    private fun pressanimation4(imageView: LottieAnimationView,animation: Int,like:Boolean):Boolean{
        if (!like){
            imageView.setAnimation(animation)
            imageView.repeatCount=5
            imageView.playAnimation()
        }else{
            imageView.setImageResource(R.mipmap.fotocasa)
        }
        return !like
    }
}