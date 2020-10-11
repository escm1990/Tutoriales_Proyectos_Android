package com.escm1990.lasbuenasnuevas

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val BASE_URL = "https://blogdebuenasnuevas.blogspot.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //SwipeRefreshLayout para que recargue la pagina con scroll hacia abajo
        idSwipe.setOnRefreshListener {
            idWebView.loadUrl(BASE_URL)
        }

        //creando el cliente webview
        idWebView.webChromeClient = object : WebChromeClient(){ }
        idWebView.webViewClient = object : WebViewClient(){

            //manejar las nuevas cargas de las URL
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return false
            }

            //ver cuando una nueva URL se va a cargar
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)

                idSwipe.isRefreshing = true
            }

            //que hacer cuando finaliza la carga de la pagina
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                idSwipe.isRefreshing = false
            }

        }

        //activar javascript
        val settings = idWebView.settings
        settings.javaScriptEnabled = true

        //cargar la url
        idWebView.loadUrl(BASE_URL)
    }

    override fun onBackPressed() {
        if(idWebView.canGoBack()){
            idWebView.goBack()
        }else{
            super.onBackPressed()
        }
    }

}