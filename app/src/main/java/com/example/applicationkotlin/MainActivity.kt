package com.example.applicationkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {

        val button = findViewById<Button>(R.id.bv1_id)
        button.setOnClickListener {
            openImagePage()
            openImagePage()
        }
    }

    private fun openImagePage() {
        val intent = Intent(this, DetailsActivity::class.java)
        startActivity(intent)
    }
}