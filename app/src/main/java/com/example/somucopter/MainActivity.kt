package com.example.somucopter

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Fullscreen - Must be called before any view logic
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        // Safety: Check if action bar exists before hiding to prevent crashes
        try {
            supportActionBar?.hide()
        } catch (e: Exception) {
            // Ignore if already hidden
        }

        // 2. Initialize WebView
        val webView = WebView(this)

        // --- TOUCH FIXES ---
        webView.isFocusable = true
        webView.isFocusableInTouchMode = true
        webView.requestFocus()
        // -------------------

        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null)

        val settings = webView.settings
        settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            allowFileAccess = true
            allowContentAccess = true
            databaseEnabled = true
            loadWithOverviewMode = true
            useWideViewPort = true
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }

        webView.webViewClient = WebViewClient()

        // 3. Set the content view
        setContentView(webView)

        // 4. Load the game
        webView.loadUrl("file:///android_asset/index.html")
    }
}