package com.mobile.campuslive

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
class AboutActivityOfCampus : AppCompatActivity() {
    private lateinit var mWebView : WebView

    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.mobile.campuslive.R.layout.activity_about_of_campus)

         mWebView = findViewById(com.mobile.campuslive.R.id.webView)
//        mWebView.loadUrl("file:///android_asset/html.html")
        mWebView.webViewClient
//        mWebView.loadUrl("https://bncet.ac.in/")

        val webSettings: WebSettings = mWebView.settings
        webSettings.javaScriptEnabled = true

        mWebView.webViewClient = com.mobile.campuslive.AboutActivityOfCampus.Callbacks()
        mWebView.loadUrl("https://bncet.ac.in/")

    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {

        if (mWebView.canGoBack()) {
            mWebView.goBack()
        } else {
            super.onBackPressed()
        }
    }
    class Callbacks : WebViewClient() {
        override fun shouldOverrideKeyEvent(view: WebView?, event: KeyEvent?): Boolean {
            return false
        }

    }
}


