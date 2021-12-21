package com.example.applicationkotlin

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        val button = findViewById<Button>(R.id.bv1_id)
        val text = findViewById<TextView>(R.id.tv_main_activity)
        button.setOnClickListener {
            text.setTextColor(Color.GREEN)
            openImagePage()
        }
    }

    private fun openImagePage() {
        val intent = Intent(this, DetailsActivity::class.java)
        startActivity(intent)
    }
}