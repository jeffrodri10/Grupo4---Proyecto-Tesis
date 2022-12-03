package com.proyecto.pruebafirebase

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

class Mensaje : AppCompatActivity() {
    lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mensaje)
        val progressBar= findViewById<ProgressBar>(R.id.progressBar) as ProgressBar
        val imagen=findViewById<ImageView>(R.id.fondomensaje)as ImageView
        webView=findViewById(R.id.webView2)
        webView?.settings?.javaScriptEnabled=true
        webView.loadUrl("https://landbot.online/v3/H-1418861-YVE1LWZRDHA2HNNM/index.html")
        webView.webViewClient= object : WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                imagen.visibility=View.VISIBLE
                progressBar.visibility = View.VISIBLE
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                Thread.sleep(5000)
                progressBar.visibility = View.GONE
                imagen.visibility=View.GONE
                super.onPageFinished(view, url)
            }
        }
    }

    override fun onBackPressed() {
        if (webView!!.canGoBack()){
            webView!!.goBack()
        }else {
            super.onBackPressed()
        }
    }
}