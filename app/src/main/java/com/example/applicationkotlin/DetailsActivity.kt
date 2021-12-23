package com.example.applicationkotlin


import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)

        initView()
    }

    private fun initView() {
        val webView = findViewById<WebView>(R.id.vw_web_view)
        val searchButton = findViewById<Button>(R.id.btn_search)
        val inputEditText = findViewById<EditText>(R.id.edt_enter)

        searchButton.setOnClickListener {
            webView.loadUrl(inputEditText.text.toString())
        }
    }
}