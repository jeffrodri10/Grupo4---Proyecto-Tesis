package com.proyecto.pruebafirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class Lugares : AppCompatActivity() {
    lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lugares)
        setWebView()
    }
    fun setWebView(){
            webView=findViewById(R.id.webView)
            webView?.settings?.javaScriptEnabled=true
            webView.loadUrl("https://app.powerbi.com/view?r=eyJrIjoiNGY5NzRkYzEtOTFjMC00MTYzLTkyNWYtMTQ4ZTM4NDM3OTdjIiwidCI6ImI0YTQwNTQ1LTc3NzktNGIzOC1hZmY3LTFmMTczOGY4MDg0MCIsImMiOjR9")
            webView.webViewClient= WebViewClient()
        }
}